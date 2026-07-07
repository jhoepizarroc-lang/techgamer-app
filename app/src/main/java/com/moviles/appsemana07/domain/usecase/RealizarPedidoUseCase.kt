package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Pedido
import com.moviles.appsemana07.domain.repository.PedidoRepository

class RealizarPedidoUseCase(private val repo: PedidoRepository) {
    suspend operator fun invoke(pedido: Pedido) = repo.realizarPedido(pedido)
}
