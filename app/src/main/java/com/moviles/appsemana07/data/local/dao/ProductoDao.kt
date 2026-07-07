package com.moviles.appsemana07.data.local.dao

import androidx.room.*
import com.moviles.appsemana07.data.local.entity.ProductoEntity

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(producto: ProductoEntity)

    @Query("SELECT * FROM productos")
    suspend fun getAll(): List<ProductoEntity>

    @Query("SELECT * FROM productos WHERE categoria = :categoria")
    suspend fun getByCategoria(categoria: String): List<ProductoEntity>

    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :query || '%'")
    suspend fun buscar(query: String): List<ProductoEntity>

    @Query("SELECT COUNT(*) FROM productos")
    suspend fun getCount(): Int

    @Query("DELETE FROM productos")
    suspend fun clearAll()
}
