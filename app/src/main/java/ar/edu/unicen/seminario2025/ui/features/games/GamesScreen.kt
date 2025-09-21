package ar.edu.unicen.seminario2025.ui.features.games


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.ui.common.GameSearchBar
import ar.edu.unicen.seminario2025.R
import ar.edu.unicen.seminario2025.ui.common.ErrorCard


@Composable
fun GamesScreen(
    viewModel: GamesViewModel,
    goDetails: (Int) -> Unit,
    goFilters: () -> Unit
) {
    val games = viewModel.games.collectAsState().value
    val isLoading = viewModel.loading.collectAsState().value
    val query = viewModel.query.collectAsState().value
    val error = viewModel.error.collectAsState().value

    if((error != null && !error.isEmpty()) && !isLoading){
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            ErrorCard(
                message = error,
                onRetry = {
                    viewModel.onApplyFilters()
                },
                modifier = Modifier.align(Alignment.Center) ,
                icon = ImageVector.vectorResource(id = R.drawable.outline_wifi_tethering_error_24)
            )
        }

        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GameSearchBar(
            query = query,
            onQueryChange = { viewModel.updateQuery(it) },
            onSearch = {

            }
        )
        Spacer(Modifier.size(35.dp))

        Row(
            modifier = Modifier
                .clickable { goFilters() }
                .padding(8.dp)
                .align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_list_24),
                contentDescription = "Filtros",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.filters_and_order),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

            GamesList(
                games = games,
                isLoading = isLoading,
                onGameClicked = goDetails ,
            )

    }
}

