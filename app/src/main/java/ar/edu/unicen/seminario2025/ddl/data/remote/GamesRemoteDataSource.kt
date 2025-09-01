package ar.edu.unicen.seminario2025.ddl.data.remote

import ar.edu.unicen.seminario2025.BuildConfig
import ar.edu.unicen.seminario2025.ddl.data.remote.api.GamesApi
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GamesRemoteDataSource @Inject constructor(
   private val gamesApi: GamesApi
) {
    private val apiKey : String = BuildConfig.API_KEY

    suspend fun getGames(): List<GameDTO> {
        return withContext(Dispatchers.IO) {
            val response = gamesApi.getGames(apiKey)
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}