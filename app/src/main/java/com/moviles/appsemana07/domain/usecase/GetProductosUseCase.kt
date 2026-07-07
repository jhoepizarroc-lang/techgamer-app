package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.repository.ProductoRepository

class GetProductosUseCase(private val repo: ProductoRepository) {
    suspend operator fun invoke(): List<Producto> {
        repo.inicializarProductosSiVacio()
        return repo.getProductos()
    }
}
