package com.moviles.appsemana07.data.repository

import com.moviles.appsemana07.data.remote.ApiService
import com.moviles.appsemana07.data.local.dao.ProductoDao
import com.moviles.appsemana07.data.local.entity.ProductoEntity
import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.repository.ProductoRepository

class ProductoRepositoryImpl(
    private val api: ApiService,
    private val dao: ProductoDao
) : ProductoRepository {
    override suspend fun getProductos(): List<Producto> {
        return try {
            val productos = api.getProductos()
            val productosConImagen = productos.map { p ->
                val resId = mapImagen(p.nombre, p.categoria)
                p.copy(imagenRes = resId)
            }

            // 1. Limpiar datos locales antiguos para sincronización total
            dao.clearAll() 

            // 2. Insertar los nuevos datos de Dynamo
            productosConImagen.forEach { p ->
                dao.insertar(
                    ProductoEntity(
                        id = p.id,
                        nombre = p.nombre,
                        descripcion = p.descripcion,
                        precio = p.precio,
                        categoria = p.categoria,
                        imagenRes = p.imagenRes
                    )
                )
            }
            productosConImagen
        } catch (e: Exception) {
            dao.getAll().map { entity ->
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
    }

    override suspend fun getProductosByCategoria(categoria: String): List<Producto> {
        return try {
            getProductos().filter { it.categoria == categoria }
        } catch (e: Exception) {
            dao.getByCategoria(categoria).map { entity ->
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
    }

    override suspend fun buscarProductos(query: String): List<Producto> {
        return try {
            getProductos().filter { it.nombre.contains(query, ignoreCase = true) }
        } catch (e: Exception) {
            dao.buscar(query).map { entity ->
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
    }

    override suspend fun inicializarProductosSiVacio() {
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
}
