package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.repository.PedidoRepository

class ObtenerPedidosUseCase(private val repo: PedidoRepository) {
    suspend operator fun invoke(usuarioId: Int) = repo.getPedidosPorUsuario(usuarioId)
}
