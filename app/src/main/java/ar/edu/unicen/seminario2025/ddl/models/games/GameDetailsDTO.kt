package ar.edu.unicen.seminario2025.ddl.models.games

import com.google.gson.annotations.SerializedName

data class GameDetailsDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("ratingTop")
    val ratingTop: Int,
    @SerializedName("ratingsCount")
    val ratingsCount: Int,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("esrbRating")
    val esrbRating: EsrbRatingDTO?,
    @SerializedName("platforms")
    val platforms: List<PlatformWrapperDTO>?
)
