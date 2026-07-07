package com.moviles.appsemana07.data.mapper

import com.moviles.appsemana07.data.local.entity.ProductoEntity
import com.moviles.appsemana07.domain.model.Producto

object ProductoMapper {
    fun toDomain(entity: ProductoEntity): Producto = Producto(
        id = entity.id,
        nombre = entity.nombre,
        descripcion = entity.descripcion,
        precio = entity.precio,
        categoria = entity.categoria,
        imagenRes = entity.imagenRes
    )

    fun toEntity(domain: Producto): ProductoEntity = ProductoEntity(
        id = domain.id,
        nombre = domain.nombre,
        descripcion = domain.descripcion,
        precio = domain.precio,
        categoria = domain.categoria,
        imagenRes = domain.imagenRes
    )
}
