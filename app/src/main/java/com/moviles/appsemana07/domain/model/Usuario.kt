package com.moviles.appsemana07.domain.model

data class Usuario(
    val id: Int = 0,
    val nombre: String,
    val username: String,
    val email: String,
    val password: String
)
