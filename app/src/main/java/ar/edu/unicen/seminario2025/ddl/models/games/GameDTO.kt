package ar.edu.unicen.seminario2025.ddl.models.games

import com.google.gson.annotations.SerializedName

data class GameDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
//    val rating: Double,
//    val ratingTop: Int,
//    val ratingsCount: Int,
//    val metacritic: Int?,
//    val updated: String,
//    val esrbRating: EsrbRatingDTO?,
//    val platforms: List<PlatformWrapperDTO>?
)
