package com.moviles.appsemana07.domain.repository

import com.moviles.appsemana07.domain.model.Pedido

interface PedidoRepository {
    suspend fun realizarPedido(pedido: Pedido)
    suspend fun getPedidosPorUsuario(usuarioId: Int): List<Pedido>
}
