package ar.edu.unicen.seminario2025.ui.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.ui.common.GameSearchBar


@Composable
fun Home() {
    var query by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            // Aplicamos el padding de systemBars
            .padding(
                WindowInsets.systemBars
                    .asPaddingValues() // Convierte a PaddingValues
            )) {
        Spacer(Modifier.size(10.dp))
        GameSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {

            }
        )
            }
}