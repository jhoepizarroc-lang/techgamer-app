package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.repository.ProductoRepository

class BuscarProductosUseCase(private val repo: ProductoRepository) {
    suspend operator fun invoke(query: String): List<Producto> {
        if (query.isBlank()) return repo.getProductos()
        return repo.buscarProductos(query)
    }
}
