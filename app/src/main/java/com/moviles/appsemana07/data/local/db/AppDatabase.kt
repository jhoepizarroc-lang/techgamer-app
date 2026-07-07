package com.moviles.appsemana07.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviles.appsemana07.data.local.dao.UsuarioDao
import com.moviles.appsemana07.data.local.dao.ProductoDao
import com.moviles.appsemana07.data.local.dao.CarritoDao
import com.moviles.appsemana07.data.local.entity.UsuarioEntity
import com.moviles.appsemana07.data.local.entity.ProductoEntity
import com.moviles.appsemana07.data.local.entity.CarritoEntity
import com.moviles.appsemana07.data.local.entity.PedidoEntity
import com.moviles.appsemana07.data.local.entity.DetallePedidoEntity
import com.moviles.appsemana07.data.local.dao.PedidoDao

@Database(
    entities = [UsuarioEntity::class, ProductoEntity::class, CarritoEntity::class, PedidoEntity::class, DetallePedidoEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDao
    abstract fun productoDao() : ProductoDao
    abstract fun carritoDao() : CarritoDao
    abstract fun pedidoDao() : PedidoDao
}
