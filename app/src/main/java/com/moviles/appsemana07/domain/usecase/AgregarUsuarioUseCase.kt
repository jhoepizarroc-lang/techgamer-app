package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class AgregarUsuarioUseCase(private val rep : UsuarioRepository) {
    suspend operator fun invoke(usuario: Usuario){
        if (usuario.nombre.isBlank()) throw Exception("Nombre vacío")
        if (usuario.username.isBlank()) throw Exception("Nombre de usuario vacío")
        if (usuario.email.isBlank()) throw Exception("Email vacío")
        if (usuario.password.isBlank()) throw Exception("Contraseña vacía")

        rep.insertar(usuario)
    }
}
