package com.moviles.appsemana07.domain.repository

import com.moviles.appsemana07.domain.model.Producto

interface CarritoRepository {
    suspend fun agregarAlCarrito(usuarioId: Int, productoId: Int)
    suspend fun getProductosEnCarrito(usuarioId: Int): List<Producto>
    suspend fun eliminarDelCarrito(usuarioId: Int, productoId: Int)
    suspend fun vaciarCarrito(usuarioId: Int)
}
