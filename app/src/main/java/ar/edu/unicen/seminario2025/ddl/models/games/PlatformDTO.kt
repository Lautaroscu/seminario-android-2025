package ar.edu.unicen.seminario2025.ddl.models.games

import androidx.annotation.Keep

@Keep

data class PlatformDTO(
    val id: Int,
    val slug: String,
    val name: String
)

