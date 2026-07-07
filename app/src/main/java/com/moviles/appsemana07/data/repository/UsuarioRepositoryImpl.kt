package com.moviles.appsemana07.data.repository

import com.moviles.appsemana07.data.remote.ApiService
import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.repository.UsuarioRepository

class UsuarioRepositoryImpl(private val api: ApiService) : UsuarioRepository {
    override suspend fun insertar(usuario: Usuario) {
        api.registrarUsuario(usuario)
    }

    override suspend fun actualizar(usuario: Usuario) {
        api.actualizarUsuario(usuario.id, usuario)
    }

    override suspend fun eliminar(usuario: Usuario) {
        api.eliminarUsuario(usuario.id)
    }

    override suspend fun getAll(): List<Usuario> = api.getUsuarios()

    override suspend fun getById(id: Int): Usuario {
        return api.getUsuarios().find { it.id == id } ?: Usuario(0, "", "", "", "")
    }

    override suspend fun login(email: String, password: String): Usuario? {
        val credentials = mapOf("email" to email, "password" to password)
        return api.login(credentials)
    }
}
