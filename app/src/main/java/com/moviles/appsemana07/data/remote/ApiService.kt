package com.moviles.appsemana07.data.remote

import com.moviles.appsemana07.domain.model.Pedido
import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.model.Usuario
import retrofit2.http.*

interface ApiService {
    @GET("usuarios")
    suspend fun getUsuarios(): List<Usuario>

    @POST("usuarios")
    suspend fun registrarUsuario(@Body usuario: Usuario)

    @PUT("usuarios/{id}")
    suspend fun actualizarUsuario(@Path("id") id: Int, @Body usuario: Usuario)

    @DELETE("usuarios/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Int)

    @POST("login")
    suspend fun login(@Body credentials: Map<String, String>): Usuario?

    @GET("productos")
    suspend fun getProductos(): List<Producto>

    @POST("carrito")
    suspend fun agregarAlCarrito(@Body item: Map<String, Int>)

    @DELETE("carrito/{usuarioId}/{productoId}")
    suspend fun eliminarDelCarrito(@Path("usuarioId") usuarioId: Int, @Path("productoId") productoId: Int)

    @DELETE("carrito/{usuarioId}")
    suspend fun vaciarCarrito(@Path("usuarioId") usuarioId: Int)

    @POST("pedidos")
    suspend fun realizarPedido(@Body pedido: Pedido)

    @GET("pedidos/{usuarioId}")
    suspend fun getPedidos(@Path("usuarioId") usuarioId: Int): List<Pedido>
}
