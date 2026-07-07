package com.moviles.appsemana07.domain.repository

import com.moviles.appsemana07.domain.model.Usuario

interface UsuarioRepository {
    suspend fun insertar(usuario: Usuario)
    suspend fun actualizar(usuario: Usuario)
    suspend fun eliminar(usuario: Usuario)
    suspend fun getAll(): List<Usuario>
    suspend fun getById(id: Int): Usuario
    suspend fun login(email: String, password: String): Usuario?
}