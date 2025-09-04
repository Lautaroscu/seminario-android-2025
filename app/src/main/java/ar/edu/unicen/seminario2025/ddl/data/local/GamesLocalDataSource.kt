package ar.edu.unicen.seminario2025.ddl.data.local

import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import javax.inject.Singleton

@Singleton
class GamesLocalDataSource {
    suspend fun getGames(): List<GameDTO> = listOf(
        GameDTO(1, "slug-1", "name1" , ""  ),
        GameDTO(2, "slug-2", "name2" , ""  )
    )
}