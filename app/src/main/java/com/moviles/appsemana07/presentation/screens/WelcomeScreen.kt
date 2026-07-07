package com.moviles.appsemana07.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moviles.appsemana07.ui.theme.*

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(MainPurple, DarkPurple)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = "Bienvenido",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.weight(1f))

            Surface(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                color = Color.White.copy(alpha = 0.2f),
                shape = CircleShape
            ) {
                Box(contentAlignment = Alignment.Center) {
                    androidx.compose.foundation.Image(
                        painter = painterResource(id = com.moviles.appsemana07.R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(140.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "TechGamer",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(ButtonGradientStart, ButtonGradientEnd)),
                        shape = RoundedCornerShape(28.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = "Ingresar", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Salir */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(ButtonGradientStart, ButtonGradientEnd)),
                        shape = RoundedCornerShape(28.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = "Salir", color = Color.White, fontSize = 18.sp)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
