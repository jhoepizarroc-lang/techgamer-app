package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class GetUsuarioUseCase(private val repo : UsuarioRepository) {
    suspend operator fun invoke(): List<Usuario>{
        return repo.getAll()
    }
}
