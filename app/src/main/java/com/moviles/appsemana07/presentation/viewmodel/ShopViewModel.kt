package com.moviles.appsemana07.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.appsemana07.domain.model.Producto
import com.moviles.appsemana07.domain.model.Usuario
import com.moviles.appsemana07.domain.usecase.ShopUseCases
import com.moviles.appsemana07.presentation.state.ShopUiState
import com.moviles.appsemana07.presentation.state.UsuarioUiEvent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShopViewModel(private val shopUseCases: ShopUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState

    private val _event = MutableSharedFlow<UsuarioUiEvent>()
    val event = _event

    init {
        cargarProductos()
    }

    fun setUsuario(usuario: Usuario) {
        _uiState.update { it.copy(usuarioLogueado = usuario) }
        cargarCarrito()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val productos = shopUseCases.getProductos()
                _uiState.update { it.copy(productos = productos, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
                _event.emit(UsuarioUiEvent.ShowSnackbar("Error al cargar productos: ${e.message}"))
            }
        }
    }

    fun buscarProductos(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(query = query, isLoading = true) }
            val productos = shopUseCases.buscarProductos(query)
            _uiState.update { it.copy(productos = productos, isLoading = false) }
        }
    }

    fun filtrarPorCategoria(categoria: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(categoriaSeleccionada = categoria, isLoading = true) }
            val productos = if (categoria == "Todas") {
                shopUseCases.getProductos()
            } else {
                shopUseCases.getProductosByCategoria(categoria)
            }
            _uiState.update { it.copy(productos = productos, isLoading = false) }
        }
    }

    fun agregarAlCarrito(producto: Producto) {
        val usuarioId = _uiState.value.usuarioLogueado?.id ?: return
        viewModelScope.launch {
            shopUseCases.gestionarCarrito.agregar(usuarioId, producto.id)
            _event.emit(UsuarioUiEvent.ShowSnackbar("${producto.nombre} agregado al carrito"))
            cargarCarrito()
        }
    }

    fun eliminarDelCarrito(producto: Producto) {
        val usuarioId = _uiState.value.usuarioLogueado?.id ?: return
        viewModelScope.launch {
            shopUseCases.gestionarCarrito.eliminar(usuarioId, producto.id)
            _event.emit(UsuarioUiEvent.ShowSnackbar("${producto.nombre} eliminado"))
            cargarCarrito()
        }
    }

    private fun cargarCarrito() {
        val usuarioId = _uiState.value.usuarioLogueado?.id ?: return
        viewModelScope.launch {
            try {
                val contenido = shopUseCases.gestionarCarrito.getContenido(usuarioId)
                _uiState.update { it.copy(carrito = contenido) }
            } catch (e: Exception) {
                _uiState.update { it.copy(carrito = emptyList()) }
                _event.emit(UsuarioUiEvent.ShowSnackbar("Error al cargar carrito: ${e.message}"))
            }
        }
    }

    fun vaciarCarrito() {
        val usuarioId = _uiState.value.usuarioLogueado?.id ?: return
        viewModelScope.launch {
            shopUseCases.gestionarCarrito.vaciar(usuarioId)
            _event.emit(UsuarioUiEvent.ShowSnackbar("Carrito vaciado"))
            cargarCarrito()
        }
    }

    fun realizarPago() {
        val usuario = _uiState.value.usuarioLogueado ?: return
        val itemsCarrito = _uiState.value.carrito
        if (itemsCarrito.isEmpty()) return

        viewModelScope.launch {
            try {
                val total = itemsCarrito.sumOf { it.precio }
                val nuevoPedido = com.moviles.appsemana07.domain.model.Pedido(
                    usuarioId = usuario.id,
                    fecha = System.currentTimeMillis(),
                    total = total,
                    detalles = itemsCarrito.map {
                        com.moviles.appsemana07.domain.model.DetallePedido(
                            productoId = it.id,
                            nombreProducto = it.nombre,
                            precio = it.precio,
                            cantidad = 1
                        )
                    }
                )
                
                shopUseCases.realizarPedido(nuevoPedido)
                shopUseCases.gestionarCarrito.vaciar(usuario.id)
                
                _event.emit(UsuarioUiEvent.ShowSnackbar("¡Compra realizada con éxito!"))
                cargarCarrito()
                cargarPedidos()
            } catch (e: Exception) {
                _event.emit(UsuarioUiEvent.ShowSnackbar("Error al procesar el pago"))
            }
        }
    }

    fun cargarPedidos() {
        val usuarioId = _uiState.value.usuarioLogueado?.id ?: return
        viewModelScope.launch {
            val pedidos = shopUseCases.obtenerPedidos(usuarioId)
            _uiState.update { it.copy(pedidos = pedidos) }
        }
    }
}
