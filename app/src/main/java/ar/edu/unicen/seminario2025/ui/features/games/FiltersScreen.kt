package ar.edu.unicen.seminario2025.ui.features.games

import GameFiltersAndSort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun GamesFiltersScreen(
    viewModel: GamesViewModel,
    navController: NavController,
) {
    val query by viewModel.query.collectAsState()
    val years by viewModel.years.collectAsState()
    val platforms by viewModel.platforms.collectAsState()
    val savedFilters by viewModel.savedFilters.collectAsState()

    GameFiltersAndSort(
        years = years,
        platforms = platforms,
        savedFilters = savedFilters,
        onApplyFilters = {
            viewModel.onApplyFilters(it)
            navController.popBackStack()
        }
    )
}
