package com.moviles.appsemana07.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviles.appsemana07.presentation.screens.*
import com.moviles.appsemana07.presentation.viewmodel.UsuarioViewModel
import com.moviles.appsemana07.presentation.viewmodel.ShopViewModel

@Composable
fun NavGraph(userVm: UsuarioViewModel, shopVm: ShopViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ){
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController, userVm, shopVm) }
        composable("register") { RegisterScreen(navController, userVm) }
        composable("register_step2") { RegisterStep2Screen(navController) }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("security_questions") { SecurityQuestionsScreen(navController) }
        
        composable("home") { HomeScreen(navController, shopVm) }
        composable("categories") { CategoryScreen(navController, shopVm) }
        composable("profile") { ProfileScreen(navController, shopVm) }
        composable("cart") { CartScreen(navController, shopVm) }
        composable("checkout") { CheckoutScreen(navController, shopVm) }
        composable("edit_profile") { EditProfileScreen(navController, userVm, shopVm) }
        composable("orders") { OrdersScreen(navController, shopVm) }
        composable("settings") { SettingsScreen(navController, userVm) }
        composable("notifications") { NotificationsScreen(navController) }
    }
}
