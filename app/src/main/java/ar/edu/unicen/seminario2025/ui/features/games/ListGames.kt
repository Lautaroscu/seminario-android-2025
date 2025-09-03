package ar.edu.unicen.seminario2025.ui.features.games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO

@Composable
fun GamesList(
    games: List<GameDTO>,
    isLoading: Boolean
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(games) { game ->
                    GameItem(
                        name = game.name,
                        released = game.released,
                        backgroundImage = game.backgroundImage
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun GamesContentPreview() {
    val mockGames = listOf(
        GameDTO(
            id = 1,
            slug = "the-witcher-3",
            name = "The Witcher 3: Wild Hunt",
            released = "2015-05-18",
            backgroundImage = "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg"
        ),
        GameDTO(
            id = 2,
            slug = "red-dead-redemption-2",
            name = "Red Dead Redemption 2",
            released = "2018-10-26",
            backgroundImage = "https://media.rawg.io/media/games/511/5118aff5091cb3efec399c808f8c598f.jpg"
        ),
        GameDTO(
            id = 3,
            slug = "god-of-war",
            name = "God of War",
            released = "2018-04-20",
            backgroundImage = "https://media.rawg.io/media/games/4be/4be6a6ad0364751a96229c56bf69be59.jpg"
        ),
        GameDTO(
            id = 4,
            slug = "hollow-knight",
            name = "Hollow Knight",
            released = "2017-02-24",
            backgroundImage = "https://media.rawg.io/media/games/d82/d82990b9c67ba0d2d09d4e6fa88885a7.jpg"
        ),
        GameDTO(
            id = 5,
            slug = "elden-ring",
            name = "Elden Ring",
            released = "2022-02-25",
            backgroundImage = "https://media.rawg.io/media/games/73e/73eecb8909e0c39fb246f457b5d6cbbe.jpg"
        ),
        GameDTO(
            id = 6,
            slug = "minecraft",
            name = "Minecraft",
            released = "2011-11-18",
            backgroundImage = "https://media.rawg.io/media/games/942/9424d6bb763dc38d9378b488603c87fa.jpg"
        ),
        GameDTO(
            id = 7,
            slug = "cyberpunk-2077",
            name = "Cyberpunk 2077",
            released = "2020-12-10",
            backgroundImage = "https://media.rawg.io/media/games/46d/46d98e6910fbc0706e2948a7cc9b10c5.jpg"
        )
    )
    GamesList(
        mockGames ,
        false
    )
}