package com.example.nema.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "NEMA", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(text = "We help maintain things", fontSize = 18.sp, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(24.dp))
        ButtonRow(navController)
    }
}

@Composable
fun ButtonRow(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = { /* TODO: Handle click */ }) {
            Text("Button 1")
        }
        Button(onClick = { /* TODO: Handle click */ }) {
            Text("Button 2")
        }
        Button(onClick = { navController.navigate("search") }) {
            Text("Search")
        }
    }
}
