import ar.edu.unicen.seminario2025.ui.features.games.enums.SortOption

data class FiltersDTO(
    var year: String? = null,
    var minRating: Float? = null,
    val platforms: List<Int>? = null,
    var query: String? = null,
    var order: SortOption? = null,
)
