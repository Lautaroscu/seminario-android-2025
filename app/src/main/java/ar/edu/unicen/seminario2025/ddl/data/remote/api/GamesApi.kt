package ar.edu.unicen.seminario2025.ddl.data.remote.api

import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey : String ,
        @Query("page_size") pageSize : Int = 20
    ): Response<GamesResponse>
}