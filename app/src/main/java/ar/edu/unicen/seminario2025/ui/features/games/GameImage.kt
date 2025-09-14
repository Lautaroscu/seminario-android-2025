import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import ar.edu.unicen.seminario2025.R
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GameImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = "Game background"
) {
    GlideImage(
        model = imageUrl ?: R.drawable.placeholder,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize(),
    )
}
