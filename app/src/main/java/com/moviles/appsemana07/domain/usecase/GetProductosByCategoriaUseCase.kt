package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.repository.ProductoRepository

class GetProductosByCategoriaUseCase(private val repo: ProductoRepository) {
    suspend operator fun invoke(categoria: String): List<Producto> {
        return repo.getProductosByCategoria(categoria)
    }
}
