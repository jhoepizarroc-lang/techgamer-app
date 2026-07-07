package com.moviles.appsemana07.domain.repository

import com.moviles.appsemana07.domain.model.Producto

interface ProductoRepository {
    suspend fun getProductos(): List<Producto>
    suspend fun getProductosByCategoria(categoria: String): List<Producto>
    suspend fun buscarProductos(query: String): List<Producto>
    suspend fun inicializarProductosSiVacio()
}
