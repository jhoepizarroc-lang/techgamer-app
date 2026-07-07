package com.moviles.appsemana07.data.local.dao

import androidx.room.*
import com.moviles.appsemana07.data.local.entity.CarritoEntity
import com.moviles.appsemana07.data.local.entity.ProductoEntity

@Dao
interface CarritoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarAlCarrito(carrito: CarritoEntity)

    @Query("""
        SELECT p.* FROM productos p 
        INNER JOIN carrito c ON p.id = c.productoId 
        WHERE c.usuarioId = :usuarioId
    """)
    suspend fun getProductosEnCarrito(usuarioId: Int): List<ProductoEntity>

    @Delete
    suspend fun eliminarDelCarrito(carrito: CarritoEntity)

    @Query("DELETE FROM carrito WHERE usuarioId = :usuarioId")
    suspend fun vaciarCarrito(usuarioId: Int)

    @Query("SELECT * FROM carrito WHERE usuarioId = :usuarioId AND productoId = :productoId LIMIT 1")
    suspend fun getCarritoItem(usuarioId: Int, productoId: Int): CarritoEntity?
}
