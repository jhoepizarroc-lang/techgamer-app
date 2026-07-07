package com.moviles.appsemana07.domain.model

data class Pedido(
    val id: Int = 0,
    val usuarioId: Int,
    val fecha: Long,
    val total: Double,
    val detalles: List<DetallePedido> = emptyList()
)

data class DetallePedido(
    val id: Int = 0,
    val pedidoId: Int = 0,
    val productoId: Int,
    val nombreProducto: String,
    val precio: Double,
    val cantidad: Int
)
