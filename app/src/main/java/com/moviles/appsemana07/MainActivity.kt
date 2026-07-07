package com.moviles.appsemana07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.moviles.appsemana07.di.AppModule
import com.moviles.appsemana07.presentation.navigation.NavGraph
import com.moviles.appsemana07.presentation.viewmodel.ShopViewModel
import com.moviles.appsemana07.presentation.viewmodel.UsuarioViewModel
import com.moviles.appsemana07.ui.theme.AppSemana07Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val userViewModel = AppModule.provideUsuarioViewModel()
        val shopViewModel = AppModule.provideShopViewModel(this)
        
        setContent {
            val uiState by userViewModel.uiState.collectAsState()
            AppSemana07Theme(darkTheme = uiState.isDarkMode) {
                NavGraph(userViewModel, shopViewModel)
            }
        }
    }
}
