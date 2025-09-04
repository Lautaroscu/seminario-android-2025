package ar.edu.unicen.seminario2025

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unicen.seminario2025.ui.features.games.GameScreen
import ar.edu.unicen.seminario2025.ui.features.games.GameViewModel
import ar.edu.unicen.seminario2025.ui.features.games.GamesScreen
import ar.edu.unicen.seminario2025.ui.features.games.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {
    private val viewModel by viewModels<GamesViewModel>()
    private val gameViewModel by viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "games" ,
            ) {
                composable(
                    route = "games" ,

                ) {

                    GamesScreen(
                        viewModel ,
                        goDetails = {
                            gameId ->
                            navController.navigate("game/${gameId}")
                        }
                    )
                }
                composable(
                    route = "game/{id}" ,
                    ) {
                    GameScreen(
                        gameViewModel
                    )
                }
            }

        }

    }
}