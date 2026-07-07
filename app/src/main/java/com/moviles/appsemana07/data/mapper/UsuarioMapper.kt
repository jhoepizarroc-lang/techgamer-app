package com.moviles.appsemana07.data.mapper

import com.moviles.appsemana07.data.local.entity.UsuarioEntity
import com.moviles.appsemana07.domain.model.Usuario

object UsuarioMapper {
    fun toDomain(entidad : UsuarioEntity) : Usuario =
        Usuario(
            id = entidad.id,
            nombre = entidad.nombre,
            username = entidad.username,
            email = entidad.email,
            password = entidad.password
        )

    fun toEntity(usuario: Usuario) : UsuarioEntity =
        UsuarioEntity(
            id = usuario.id,
            nombre = usuario.nombre,
            username = usuario.username,
            email = usuario.email,
            password = usuario.password
        )
}
