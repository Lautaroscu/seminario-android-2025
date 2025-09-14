package ar.edu.unicen.seminario2025.ddl.data.remote

import FiltersDTO
import ar.edu.unicen.seminario2025.BuildConfig
import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import ar.edu.unicen.seminario2025.ddl.data.remote.api.GamesApi
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import ar.edu.unicen.seminario2025.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GamesRemoteDataSource @Inject constructor(
   private val gamesApi: GamesApi
) {
    private val apiKey: String = BuildConfig.API_KEY

    suspend fun getGames(filtersDTO: FiltersDTO?): ApiResult<List<GameDTO>> {
        return safeApiCall {
            val query = filtersDTO?.query
            val rating = filtersDTO?.minRating
            var platforms = filtersDTO?.platforms?.joinToString(",")
            if (platforms?.isEmpty() == true) {
                platforms = null
            }
            val dates = filtersDTO?.year?.let { "$it,$it" }
            val order = filtersDTO?.order

            val response = gamesApi.getGames(
                apiKey = apiKey,
                search = query,
                platforms = platforms,
                dates = dates,
                rating = rating,
                ordering = order?.apiValue
            )

            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                throw HttpException(response)
            }
        }
    }


    suspend fun getGame(gameId: Int): ApiResult<GameDetailsDTO> {
        return safeApiCall {
            val response = gamesApi.getGame(gameId, apiKey)
            if (response.isSuccessful) {
                response.body() ?: throw IOException("Response body is null")
            } else {
                throw HttpException(response)
            }
        }
    }
}