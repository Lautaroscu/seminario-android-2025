package ar.edu.unicen.seminario2025.ui.features.games.enums

import ar.edu.unicen.seminario2025.R

enum class SortOption(val apiValue: String, val labelRes: Int) {
    NAME_ASC("name", R.string.sort_name_asc),
    NAME_DESC("-name", R.string.sort_name_desc),
    RELEASED_DESC("-released", R.string.sort_recent),
    RELEASED_ASC("released", R.string.sort_oldest),
    RATING_ASC("rating", R.string.sort_rating_asc),
    RATING_DESC("-rating", R.string.sort_rating_desc),
    METACRITIC_ASC("metacritic", R.string.sort_metacritic_asc),
    METACRITIC_DESC("-metacritic", R.string.sort_metacritic_desc)
}