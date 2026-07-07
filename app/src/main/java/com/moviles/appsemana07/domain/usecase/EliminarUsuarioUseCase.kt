package com.moviles.appsemana07.domain.usecase

import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class EliminarUsuarioUseCase(private val repo : UsuarioRepository) {
    suspend operator fun invoke(usuario: Usuario){
        if(usuario.id <= 0) throw Exception("Usuario inválido")
        repo.eliminar(usuario)
    }
}

