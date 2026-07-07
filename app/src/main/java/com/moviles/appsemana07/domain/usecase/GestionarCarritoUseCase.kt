package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.repository.CarritoRepository

class GestionarCarritoUseCase(private val repo: CarritoRepository) {
    suspend fun agregar(usuarioId: Int, productoId: Int) = repo.agregarAlCarrito(usuarioId, productoId)
    suspend fun getContenido(usuarioId: Int) = repo.getProductosEnCarrito(usuarioId)
    suspend fun eliminar(usuarioId: Int, productoId: Int) = repo.eliminarDelCarrito(usuarioId, productoId)
    suspend fun vaciar(usuarioId: Int) = repo.vaciarCarrito(usuarioId)
}
