package ar.edu.unicen.seminario2025.ui.features.games

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ar.edu.unicen.seminario2025.R
import ar.edu.unicen.seminario2025.ui.common.ErrorCard
import ar.edu.unicen.seminario2025.utils.getImageUriFromUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GameDetailsScreen(
        gameViewModel: GameViewModel,
        gameId : Int,
        modifier: Modifier = Modifier,
        goBack : () -> Unit
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
                                val context = LocalContext.current
                                GameDetailsScreenContent(
                                        game  ,
                                        goBack ,
                                        shareGame = { shareGame(context, game.name, game.website , game.backgroundImage) }
                                )
                        }
                }


        }
}
fun shareGame(context: Context, gameName: String, gameUrl: String?, imageUrl: String?) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
                val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                                Intent.EXTRA_TEXT,
                                "${context.getString(R.string.share_message)} 🎮 $gameName\n$gameUrl"
                        )

                        if (!imageUrl.isNullOrEmpty()) {
                                val imageUri = getImageUriFromUrl(context, imageUrl)
                                putExtra(Intent.EXTRA_STREAM, imageUri)
                                type = "image/*"
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        } else {
                                type = "text/plain"
                        }
                }

                withContext(Dispatchers.Main) {
                        context.startActivity(
                                Intent.createChooser(intent, "${context.getString(R.string.share)} ${context.getString(R.string.game)}")
                        )
                }
        }
}



