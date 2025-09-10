package ar.edu.unicen.seminario2025.ddl.data.remote

import FiltersDTO
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

    suspend fun getGames(filtersDTO: FiltersDTO? ): List<GameDTO> {
        return withContext(Dispatchers.IO) {
            val query = filtersDTO?.query
            val rating = filtersDTO?.minRating

            var  platforms = filtersDTO?.platforms?.joinToString(",")
            if(platforms?.isEmpty() == true) {
                platforms = null
            }
            val dates = filtersDTO?.year?.let { "$it,${it}" }
            val order = filtersDTO?.order
            val response = gamesApi.getGames(
                apiKey = apiKey ,
                search = query,
                platforms = platforms,
                dates = dates,
                rating = rating,
                ordering = order?.apiValue
            )
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
    suspend fun searchGames(query : String): List<GameDTO> {
        return withContext(Dispatchers.IO) {
            val response = gamesApi.getGames(apiKey , query)
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