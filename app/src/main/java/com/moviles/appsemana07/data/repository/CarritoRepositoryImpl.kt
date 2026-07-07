package com.moviles.appsemana07.data.repository

import com.moviles.appsemana07.data.remote.ApiService
import com.moviles.appsemana07.data.local.dao.CarritoDao
import com.moviles.appsemana07.data.local.entity.CarritoEntity
import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.repository.CarritoRepository

class CarritoRepositoryImpl(
    private val api: ApiService,
    private val dao: CarritoDao
) : CarritoRepository {
    override suspend fun agregarAlCarrito(usuarioId: Int, productoId: Int) {
        val existente = dao.getCarritoItem(usuarioId, productoId)
        if (existente == null) {
            dao.agregarAlCarrito(CarritoEntity(usuarioId = usuarioId, productoId = productoId, cantidad = 1))
        } else {
            dao.agregarAlCarrito(existente.copy(cantidad = existente.cantidad + 1))
        }
        
        try {
            val item = mapOf("usuarioId" to usuarioId, "productoId" to productoId, "cantidad" to 1)
            api.agregarAlCarrito(item)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getProductosEnCarrito(usuarioId: Int): List<Producto> {
        val entities = dao.getProductosEnCarrito(usuarioId)
        return entities.map { entity ->
            Producto(
                id = entity.id,
                nombre = entity.nombre,
                descripcion = entity.descripcion,
                precio = entity.precio,
                categoria = entity.categoria,
                imagenRes = mapImagen(entity.nombre, entity.categoria)
            )
        }
    }

    private fun mapImagen(nombre: String, categoria: String): Int {
        return when {
            nombre.contains("Laptop", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.laptop
            nombre.contains("Mouse", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.mouse
            nombre.contains("Teclado", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.teclado
            nombre.contains("Monitor", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.monitor
            nombre.contains("Auricular", ignoreCase = true) || nombre.contains("Headset", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.auriculares
            nombre.contains("Silla", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.sillagamer
            nombre.contains("SSD", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.ssd
            nombre.contains("Componente", ignoreCase = true) || categoria.contains("Componente", ignoreCase = true) -> com.moviles.appsemana07.R.drawable.componente
            else -> com.moviles.appsemana07.R.drawable.logo
        }
    }

    override suspend fun eliminarDelCarrito(usuarioId: Int, productoId: Int) {
        val item = dao.getCarritoItem(usuarioId, productoId)
        if (item != null) {
            dao.eliminarDelCarrito(item)
        }
        try {
            api.eliminarDelCarrito(usuarioId, productoId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun vaciarCarrito(usuarioId: Int) {
        dao.vaciarCarrito(usuarioId)
        try {
            api.vaciarCarrito(usuarioId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
