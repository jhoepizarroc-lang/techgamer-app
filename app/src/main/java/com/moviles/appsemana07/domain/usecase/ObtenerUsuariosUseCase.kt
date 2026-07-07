package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class ObtenerUsuariosUseCase(private val repository: UsuarioRepository) {
    suspend operator fun invoke(): List<Usuario> {
        return repository.getAll()
    }
}
