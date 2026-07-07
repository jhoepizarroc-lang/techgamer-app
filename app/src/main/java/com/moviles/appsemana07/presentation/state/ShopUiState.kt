package com.moviles.appsemana07.presentation.state

import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.model.Usuario

import com.moviles.appsemana07.domain.model.Pedido

data class ShopUiState(
    val productos: List<Producto> = emptyList(),
    val carrito: List<Producto> = emptyList(),
    val pedidos: List<Pedido> = emptyList(),
    val usuarioLogueado: Usuario? = null,
    val isLoading: Boolean = false,
    val query: String = "",
    val categoriaSeleccionada: String = "Todas"
)
