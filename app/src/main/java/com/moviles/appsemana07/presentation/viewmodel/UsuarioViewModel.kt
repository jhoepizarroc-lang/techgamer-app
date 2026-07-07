package com.moviles.appsemana07.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.usecase.UsuarioUseCases
import com.moviles.appsemana07.presentation.state.UsuarioUiEvent
import com.moviles.appsemana07.presentation.state.UsuarioUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel(private val usuarioUseCases: UsuarioUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState: StateFlow<UsuarioUiState> = _uiState

    private val _event = MutableSharedFlow<UsuarioUiEvent>()
    val event = _event

    fun onNombreChange(nombre: String) {
        _uiState.update { it.copy(nombre = nombre) }
    }

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
    }

    fun agregarUsuario(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val usuario = Usuario(
                nombre = _uiState.value.nombre,
                username = _uiState.value.username,
                email = _uiState.value.email,
                password = _uiState.value.password
            )
            usuarioUseCases.agregarUsuario(usuario)
            _event.emit(UsuarioUiEvent.ShowSnackbar("Usuario registrado con éxito"))
            onSuccess()
        }
    }

    fun login(email: String, password: String, onResult: (Usuario?) -> Unit) {
        viewModelScope.launch {
            val usuario = usuarioUseCases.login(email, password)
            if (usuario != null) {
                _uiState.update { it.copy(
                    nombre = usuario.nombre,
                    username = usuario.username,
                    email = usuario.email,
                    password = usuario.password
                ) }
                onResult(usuario)
            } else {
                _event.emit(UsuarioUiEvent.ShowSnackbar("Credenciales incorrectas"))
                onResult(null)
            }
        }
    }

    fun actualizarPerfil(usuarioId: Int, onSuccess: (Usuario) -> Unit) {
        viewModelScope.launch {
            try {
                val usuarioActualizado = Usuario(
                    id = usuarioId,
                    nombre = _uiState.value.nombre,
                    username = _uiState.value.username,
                    email = _uiState.value.email,
                    password = _uiState.value.password
                )
                usuarioUseCases.actualizarUsuario(usuarioActualizado)
                _event.emit(UsuarioUiEvent.ShowSnackbar("Perfil actualizado correctamente"))
                onSuccess(usuarioActualizado)
            } catch (e: Exception) {
                _event.emit(UsuarioUiEvent.ShowSnackbar(e.message ?: "Error al actualizar"))
            }
        }
    }
}
