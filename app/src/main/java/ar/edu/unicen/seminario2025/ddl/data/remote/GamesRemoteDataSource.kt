package ar.edu.unicen.seminario2025.ddl.data.remote

import ar.edu.unicen.seminario2025.BuildConfig
import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import ar.edu.unicen.seminario2025.ddl.data.remote.api.GamesApi
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
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


    suspend fun getGame(gameId: Int): ApiResult<GameDetailsDTO> {
        return try {
            val response = gamesApi.getGame(gameId, apiKey)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ApiResult.Success(body)
                } else {
                    ApiResult.Error(Exception("Empty body"))
                }
            } else {
                ApiResult.Error(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }


}