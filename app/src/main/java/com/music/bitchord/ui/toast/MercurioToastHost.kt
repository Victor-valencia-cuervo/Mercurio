package com.music.bitchord.ui.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object MercurioToasts {
    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> = _messages

    fun show(msg: String) {
        _messages.value = _messages.value + msg
    }

    fun dismiss(msg: String) {
        _messages.value = _messages.value - msg
    }
}

@Composable
fun MercurioToastHost() {
    val msgs by MercurioToasts.messages.collectAsState()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(Modifier.padding(top = 48.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            msgs.forEach { m ->
                AnimatedVisibility(visible = true) {
                    Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 4.dp) {
                        Text(m, Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
                    }
                }
            }
        }
    }
}