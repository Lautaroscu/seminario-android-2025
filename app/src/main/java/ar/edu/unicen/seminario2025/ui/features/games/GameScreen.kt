package ar.edu.unicen.seminario2025.ui.features.games

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.ui.common.ErrorCard

@Composable
fun GameDetailsScreen(
        gameViewModel: GameViewModel,
        gameId : Int,
        modifier: Modifier = Modifier
) {
        LaunchedEffect(gameId) {
                gameId.let { id ->
                        gameViewModel.getGame(id)
                }
        }
        val game = gameViewModel.game.collectAsState().value
        val loading = gameViewModel.loading.collectAsState().value
        val error = gameViewModel.error.collectAsState().value

        Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
        ) {
                when {
                        loading -> {
                                CircularProgressIndicator()
                        }

                        game == null || error -> {
                                ErrorCard(
                                        message = "Ocurrió un error al cargar el juego",
                                        onRetry = { gameViewModel.getGame(gameId) }
                                )
                        }

                        else -> {
                                GameDetailsScreenContent(game)
                        }
                }


        }
}
