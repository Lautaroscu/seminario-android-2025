package ar.edu.unicen.seminario2025.ddl.models.games

data class GamesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameDTO>
)
