package ar.edu.unicen.seminario2025

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unicen.seminario2025.ui.AppNavigation
import ar.edu.unicen.seminario2025.ui.common.Seminario2025Theme
import ar.edu.unicen.seminario2025.ui.features.games.GameDetailsScreen
import ar.edu.unicen.seminario2025.ui.features.games.GameViewModel
import ar.edu.unicen.seminario2025.ui.features.games.GamesFiltersScreen
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
            Seminario2025Theme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {
                    AppNavigation(
                       gameViewModel = gameViewModel,
                       viewModel = viewModel,
                      navController = navController
                    )
                }


            }
            }

    }

}
