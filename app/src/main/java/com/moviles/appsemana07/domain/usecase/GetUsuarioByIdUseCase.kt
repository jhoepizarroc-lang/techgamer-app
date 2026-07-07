package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class GetUsuarioByIdUseCase(private val repo : UsuarioRepository) {
    suspend operator fun invoke(id: Int): Usuario {
        if (id < 0) throw Exception("ID inválido")
        return repo.getById(id)
    }
}
