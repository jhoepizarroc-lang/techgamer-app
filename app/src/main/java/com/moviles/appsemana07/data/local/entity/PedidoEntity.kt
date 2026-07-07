package com.moviles.appsemana07.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class PedidoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usuarioId: Int,
    val fecha: Long,
    val total: Double
)

@Entity(tableName = "detalle_pedidos")
data class DetallePedidoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pedidoId: Int,
    val productoId: Int,
    val nombreProducto: String,
    val precio: Double,
    val cantidad: Int
)
