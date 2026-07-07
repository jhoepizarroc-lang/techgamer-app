package com.moviles.appsemana07.domain.usecase

data class UsuarioUseCases(
    val agregarUsuario: AgregarUsuarioUseCase,
    val obtenerUsuarios: ObtenerUsuariosUseCase,
    val actualizarUsuario: ActualizarUsuarioUseCase,
    val eliminarUsuario: EliminarUsuarioUseCase,
    val login: LoginUseCase
)
