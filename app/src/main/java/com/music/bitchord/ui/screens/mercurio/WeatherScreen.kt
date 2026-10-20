package com.music.bitchord.ui.screens.mercurio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.music.bitchord.data.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun WeatherScreen(onBack: () -> Unit) {
    var weather by remember { mutableStateOf<com.music.bitchord.data.weather.WeatherCurrent?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        loading = true
        try {
            weather = withContext(Dispatchers.IO) { WeatherRepository.fetch(5.8747, -75.5667, true) }
            error = null
        } catch (e: Exception) {
            error = e.message
        } finally {
            loading = false
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Clima", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onBack) { Text("Volver") }
        }
        Spacer(Modifier.height(16.dp))
        if (loading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text("Error: $error")
        } else {
            weather?.let { w ->
                Text("${w.temp}${w.sym}", style = MaterialTheme.typography.displayLarge)
                Text(w.title, style = MaterialTheme.typography.titleLarge)
                Text(w.advice)
                Spacer(Modifier.height(8.dp))
                Text("Máx ${w.max}${w.sym} / Mín ${w.min}${w.sym}")
                Text("Lluvia ${w.pop}%  UV ${w.uv}")
                Text("Sensación ${w.feels}${w.sym}")
            }
        }
    }
}