package com.moviles.appsemana07.presentation.state

data class UsuarioUiState(
    val nombre: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isDarkMode: Boolean = false
)
