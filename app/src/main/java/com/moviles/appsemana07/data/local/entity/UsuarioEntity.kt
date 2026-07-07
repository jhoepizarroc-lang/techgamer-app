package com.moviles.appsemana07.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviles.appsemana07.domain.model.Usuario

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val username: String,
    val email: String,
    val password: String
)

fun UsuarioEntity.toDomain() = Usuario(id, nombre, username, email, password)
fun Usuario.toEntity() = UsuarioEntity(id, nombre, username, email, password)
