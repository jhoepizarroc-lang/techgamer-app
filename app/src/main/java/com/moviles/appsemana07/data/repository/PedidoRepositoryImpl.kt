package com.moviles.appsemana07.data.repository

import com.moviles.appsemana07.data.remote.ApiService
import com.moviles.appsemana07.data.local.dao.PedidoDao
import com.moviles.appsemana07.domain.model.Pedido
import com.moviles.appsemana07.domain.repository.PedidoRepository

class PedidoRepositoryImpl(
    private val api: ApiService,
    private val dao: PedidoDao
) : PedidoRepository {
    override suspend fun realizarPedido(pedido: Pedido) {
        try {
            api.realizarPedido(pedido)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getPedidosPorUsuario(usuarioId: Int): List<Pedido> {
        return try {
            api.getPedidos(usuarioId)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
