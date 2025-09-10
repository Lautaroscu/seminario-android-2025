package ar.edu.unicen.seminario2025.ui.features.games

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.ddl.models.games.EsrbRatingDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import ar.edu.unicen.seminario2025.ddl.models.games.PlatformDTO
import ar.edu.unicen.seminario2025.ddl.models.games.PlatformWrapperDTO
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GameDetailsScreenContent(
    game: GameDetailsDTO,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Imagen principal
        game.backgroundImage?.let { imageUrl ->
            GlideImage(
                model = imageUrl,
                contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Nombre y fecha
        Text(
            text = game.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold ,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Lanzamiento: ${game.released}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        game.esrbRating?.let {
            Text(
                text = "Clasificación ESRB: ${it.name}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rating
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "⭐ ${game.rating} / ${game.ratingTop}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "(${game.ratingsCount} votos)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Metacritic adaptado
        game.metacritic?.let { score ->
            Spacer(modifier = Modifier.height(8.dp))
            val metacriticColor = when {
                score >= 75 -> MaterialTheme.colorScheme.tertiary   // verde adaptado al tema
                score >= 50 -> MaterialTheme.colorScheme.secondary  // amarillo/naranja adaptado
                else -> MaterialTheme.colorScheme.error             // rojo adaptado
            }
            Text(
                text = "Metacritic: $score",
                style = MaterialTheme.typography.bodyMedium,
                color = metacriticColor
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Plataformas
        game.platforms?.let { platforms ->
            Text(
                text = "Plataformas:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                platforms.forEach { platformWrapper ->
                    Text(
                        text = platformWrapper.platform.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Última actualización
        Text(
            text = "Última actualización: ${game.updated}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GameDetailsPreview() {
    val gtaV = GameDetailsDTO(
        id = 3498,
        slug = "grand-theft-auto-v",
        name = "Grand Theft Auto V",
        released = "2013-09-17",
        backgroundImage = "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
        rating = 4.47,
        ratingTop = 5,
        ratingsCount = 4314 + 2394 + 464, // suma de las categorías
        metacritic = 92,
        updated = "2025-09-03T13:19:21",
        esrbRating = EsrbRatingDTO(
            id = 1,
            name = "Mature",
            slug = "mature"
        ),
        platforms = listOf(
            PlatformWrapperDTO(PlatformDTO(4, "PC", "pc")),
            PlatformWrapperDTO(PlatformDTO(18, "PlayStation 4", "playstation4")),
            PlatformWrapperDTO(PlatformDTO(1, "Xbox One", "xbox-one"))
        )
    )

    MaterialTheme {
        GameDetailsScreenContent(
            game = gtaV,
            modifier = Modifier.fillMaxSize()
        )
    }
}
