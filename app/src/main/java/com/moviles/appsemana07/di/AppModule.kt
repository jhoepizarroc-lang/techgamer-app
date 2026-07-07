package com.moviles.appsemana07.di

import android.content.Context
import com.moviles.appsemana07.data.repository.UsuarioRepositoryImpl
import com.moviles.appsemana07.data.repository.ProductoRepositoryImpl
import com.moviles.appsemana07.data.repository.CarritoRepositoryImpl
import com.moviles.appsemana07.data.repository.PedidoRepositoryImpl
import com.moviles.appsemana07.domain.repository.UsuarioRepository
import com.moviles.appsemana07.domain.repository.ProductoRepository
import com.moviles.appsemana07.domain.repository.CarritoRepository
import com.moviles.appsemana07.domain.repository.PedidoRepository
import com.moviles.appsemana07.domain.usecase.*
import com.moviles.appsemana07.presentation.viewmodel.UsuarioViewModel
import com.moviles.appsemana07.presentation.viewmodel.ShopViewModel
import com.moviles.appsemana07.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import androidx.room.Room
import com.moviles.appsemana07.data.local.db.AppDatabase
import com.moviles.appsemana07.data.local.dao.CarritoDao
import com.moviles.appsemana07.data.local.dao.ProductoDao
import com.moviles.appsemana07.data.local.dao.PedidoDao

object AppModule {

    private const val BASE_URL = "https://daiovy4rf6.execute-api.us-east-1.amazonaws.com/"
    private var database: AppDatabase? = null

    private fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration().build()
            database = instance
            instance
        }
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private fun provideUsuarioRepository(): UsuarioRepository =
        UsuarioRepositoryImpl(apiService)

    private fun provideProductoRepository(context: Context): ProductoRepository =
        ProductoRepositoryImpl(apiService, getDatabase(context).productoDao())

    private fun provideCarritoRepository(context: Context): CarritoRepository =
        CarritoRepositoryImpl(apiService, getDatabase(context).carritoDao())

    private fun providePedidoRepository(context: Context): PedidoRepository =
        PedidoRepositoryImpl(apiService, getDatabase(context).pedidoDao())

    fun provideUsuarioUseCases(): UsuarioUseCases {
        val repo = provideUsuarioRepository()
        return UsuarioUseCases(
            agregarUsuario = AgregarUsuarioUseCase(repo),
            obtenerUsuarios = ObtenerUsuariosUseCase(repo),
            actualizarUsuario = ActualizarUsuarioUseCase(repo),
            eliminarUsuario = EliminarUsuarioUseCase(repo),
            login = LoginUseCase(repo)
        )
    }

    fun provideShopUseCases(context: Context): ShopUseCases {
        val pRepo = provideProductoRepository(context)
        val cRepo = provideCarritoRepository(context)
        val pedRepo = providePedidoRepository(context)
        return ShopUseCases(
            getProductos = GetProductosUseCase(pRepo),
            buscarProductos = BuscarProductosUseCase(pRepo),
            getProductosByCategoria = GetProductosByCategoriaUseCase(pRepo),
            gestionarCarrito = GestionarCarritoUseCase(cRepo),
            realizarPedido = RealizarPedidoUseCase(pedRepo),
            obtenerPedidos = ObtenerPedidosUseCase(pedRepo)
        )
    }

    fun provideUsuarioViewModel(): UsuarioViewModel =
        UsuarioViewModel(provideUsuarioUseCases())

    fun provideShopViewModel(context: Context): ShopViewModel =
        ShopViewModel(provideShopUseCases(context))
}
