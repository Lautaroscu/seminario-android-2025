package ar.edu.unicen.seminario2025.ddl.data.remote.api

import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {
        @GET("games")
        suspend fun getGames(
            @Query("key") apiKey: String,
            @Query("search") search: String? = null,
            @Query("platforms") platforms: String? = null,
            @Query("dates") dates: String? = null,
            @Query("rating") rating: Float? = null,
            @Query("page_size") pageSize: Int = 20,
            @Query("ordering") ordering: String? = null
        ): Response<GamesResponse>

    //get By Id
    @GET("games/{id}")
    suspend fun getGame(
        @Path("id") id : Int ,
        @Query("key") apiKey : String
    ): Response<GameDetailsDTO>
}