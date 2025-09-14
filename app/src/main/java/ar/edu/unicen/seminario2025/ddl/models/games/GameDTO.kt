package ar.edu.unicen.seminario2025.ddl.models.games

import com.google.gson.annotations.SerializedName

data class GameDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String? = null,
    @SerializedName("background_image")
    val backgroundImage: String? = null,
    @SerializedName("rating")
    val rating: Float? = 0F,
    @SerializedName("platforms")
    val platforms: List<PlatformWrapperDTO>? = null,
)
