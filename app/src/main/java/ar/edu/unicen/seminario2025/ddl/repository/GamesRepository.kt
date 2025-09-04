package ar.edu.unicen.seminario2025.ddl.repository

import ar.edu.unicen.seminario2025.ddl.data.local.GamesLocalDataSource
import ar.edu.unicen.seminario2025.ddl.data.remote.GamesRemoteDataSource
import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val remoteDataSource: GamesRemoteDataSource
){

    suspend fun getGames() : List<GameDTO> {
        return  remoteDataSource.getGames()
    }
    suspend fun getGame(gameId : Int) : ApiResult<GameDetailsDTO> {
        return  remoteDataSource.getGame(gameId)
    }
}