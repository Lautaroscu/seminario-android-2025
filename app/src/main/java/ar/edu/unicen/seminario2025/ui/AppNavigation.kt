package ar.edu.unicen.seminario2025.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unicen.seminario2025.ui.features.games.GameDetailsScreen
import ar.edu.unicen.seminario2025.ui.features.games.GameViewModel
import ar.edu.unicen.seminario2025.ui.features.games.GamesFiltersScreen
import ar.edu.unicen.seminario2025.ui.features.games.GamesScreen
import ar.edu.unicen.seminario2025.ui.features.games.GamesViewModel

@Composable
fun AppNavigation(
    gameViewModel: GameViewModel ,
    viewModel: GamesViewModel,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = "games",
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        composable(
            route = "games",
        ) {

            GamesScreen(
                viewModel,
                goDetails = { gameId ->
                    navController.navigate("game/${gameId}")
                } ,
                goFilters =  {
                    navController.navigate("games/filters")
                }
            )
        }
        composable(
            route = "game/{id}"
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("id")?.toInt() ?: 0

            GameDetailsScreen(
                gameViewModel = gameViewModel,
                gameId = gameId,
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "games/filters"
        ) {
            GamesFiltersScreen(
                viewModel,
                navController
            )
        }

    }
}