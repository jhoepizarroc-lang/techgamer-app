package com.moviles.appsemana07.presentation.state

sealed class UsuarioUiEvent {
    data class ShowSnackbar(val mensaje: String): UsuarioUiEvent()
    object NavigateBack : UsuarioUiEvent()
}