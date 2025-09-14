package ar.edu.unicen.seminario2025.ddl.repository

import FiltersDTO
import ar.edu.unicen.seminario2025.ddl.data.remote.GamesRemoteDataSource
import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import okio.IOException
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val remoteDataSource: GamesRemoteDataSource){

    suspend fun getGames(filters : FiltersDTO? = null) : ApiResult<List<GameDTO>> {
            return remoteDataSource.getGames(filters)
    }
    suspend fun getGame(gameId : Int) : ApiResult<GameDetailsDTO> {
        return  remoteDataSource.getGame(gameId)
    }
}