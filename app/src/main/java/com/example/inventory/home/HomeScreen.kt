package com.example.inventory.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.base.ui.composables.HighSpace
import app.base.ui.composables.MediumSpace

@Composable
fun HomeScreen(navController: NavHostController) {
    OptionsScreen(navController)
}


@Composable
fun OptionsScreen(navController: NavHostController){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Espacio distribuido uniformemente
        ) {
            // Título "Bienvenido"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bienvenido",
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
            }

            HighSpace()
            HorizontalDivider()
            MediumSpace()

            // Sección "Productos"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Define cuánto espacio ocupa este texto
                contentAlignment = Alignment.CenterStart // Centra el contenido en su espacio
            ) {
                Text(
                    text = "- Productos",
                    fontSize = 30.sp,
                    modifier = Modifier.clickable { }
                )
            }

            MediumSpace()
            HorizontalDivider()
            MediumSpace()

            // Sección "Categorias"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.CenterStart // Centra el contenido en su espacio
            ) {
                Text(
                    text = "- Categorias",
                    fontSize = 30.sp,
                    modifier = Modifier.clickable { }
                )
            }

            MediumSpace()
            HorizontalDivider()
            MediumSpace()

            // Sección "Inventario"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.CenterStart // Centra el contenido en su espacio
            ) {
                Text(
                    text = "- Inventario",
                    fontSize = 30.sp,
                    modifier = Modifier.clickable { }
                )
            }

            MediumSpace()
            HorizontalDivider()
            MediumSpace()
        }
    }




}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}