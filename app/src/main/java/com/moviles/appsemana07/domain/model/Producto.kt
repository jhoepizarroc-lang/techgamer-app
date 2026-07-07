package com.moviles.appsemana07.domain.model

data class Producto(
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val imagenRes: Int
)
