package com.moviles.appsemana07.domain.usecase

data class ShopUseCases(
    val getProductos: GetProductosUseCase,
    val buscarProductos: BuscarProductosUseCase,
    val getProductosByCategoria: GetProductosByCategoriaUseCase,
    val gestionarCarrito: GestionarCarritoUseCase,
    val realizarPedido: RealizarPedidoUseCase,
    val obtenerPedidos: ObtenerPedidosUseCase
)
