package com.music.bitchord.ui.screens.mercurio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.music.bitchord.R

@Composable
fun PetsScreen(onBack: () -> Unit) {
    var mood by remember { mutableStateOf("Feliz") }
    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Mascotas", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onBack) { Text("Volver") }
        }
        Spacer(Modifier.height(24.dp))
        Image(painter = painterResource(id = R.drawable.mercurio_pet), contentDescription = "Mascota", modifier = Modifier.size(180.dp))
        Spacer(Modifier.height(12.dp))
        Text("Estado: $mood", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))
        Row {
            Button(onClick = { mood = "Feliz" }) { Text("Feliz") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { mood = "Dormido" }) { Text("Dormido") }
        }
    }
}