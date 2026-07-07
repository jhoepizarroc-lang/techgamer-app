package com.moviles.appsemana07.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.moviles.appsemana07.data.local.entity.DetallePedidoEntity
import com.moviles.appsemana07.data.local.entity.PedidoEntity

@Dao
interface PedidoDao {
    @Insert
    suspend fun insertarPedido(pedido: PedidoEntity): Long

    @Insert
    suspend fun insertarDetalles(detalles: List<DetallePedidoEntity>)

    @Transaction
    @Query("SELECT * FROM pedidos WHERE usuarioId = :usuarioId ORDER BY fecha DESC")
    suspend fun getPedidosPorUsuario(usuarioId: Int): List<PedidoEntity>

    @Query("SELECT * FROM detalle_pedidos WHERE pedidoId = :pedidoId")
    suspend fun getDetallesPorPedido(pedidoId: Int): List<DetallePedidoEntity>
}
