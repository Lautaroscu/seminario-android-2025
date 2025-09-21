package ar.edu.unicen.seminario2025.ui.features.games

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.R
import ar.edu.unicen.seminario2025.ddl.models.games.EsrbRatingDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GameDetailsScreenContent(
    game: GameDetailsDTO,
    goBack : () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .clickable { goBack() }
                .padding(8.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.back),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
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

        Text(
            text = game.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold ,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Lanzamiento: ${game.released?.let { 
                if(it.isEmpty() || it == "null") {
                    "No disponible"
                }else{
                    formatDate(it)
                }
            }}",
            style = MaterialTheme.typography.bodyMedium,
        )

        game.esrbRating?.let {
            Text(
                text = "Clasificación ESRB: ${it.name}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "⭐ ${game.rating} / ${game.ratingTop}",
                style = MaterialTheme.typography.bodyMedium,

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "(${game.ratingsCount} votos)",
                style = MaterialTheme.typography.bodySmall,
            )
        }

        game.metacritic?.let { score ->
            Spacer(modifier = Modifier.height(8.dp))
            val metacriticColor = when {
                score >= 75 -> MaterialTheme.colorScheme.tertiary
                score >= 50 -> MaterialTheme.colorScheme.secondary
                else -> MaterialTheme.colorScheme.error
            }
            Text(
                text = "Metacritic: $score",
                style = MaterialTheme.typography.bodyMedium,
                color = metacriticColor
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        game.platforms.let { platforms ->
            Text(
                text = stringResource(R.string.platforms),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
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



        Text(
            text = "Última actualización: ${formatDate(game.updated)}",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
fun formatDate(dateStr: String): String {
    return try {
        val parsed = LocalDateTime.parse(dateStr)
        parsed.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault()))
    } catch (e: Exception) {
        try {
            LocalDate.parse(dateStr)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()))
        } catch (e: Exception) {
            dateStr
        }
    }
}

