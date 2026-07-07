package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class LoginUseCase(private val repo: UsuarioRepository) {
    suspend operator fun invoke(email: String, password: String): Usuario? {
        if (email.isBlank() || password.isBlank()) throw Exception("Campos vacíos")
        return repo.login(email, password)
    }
}
