package ar.edu.unicen.seminario2025.utils

import FiltersDTO
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import ar.edu.unicen.seminario2025.ui.features.games.enums.SortOption
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class FiltersPreferences @Inject constructor(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("game_filters", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_YEAR = "filter_year"
        private const val KEY_MIN_RATING = "filter_min_rating"
        private const val KEY_PLATFORMS = "filter_platforms"
        private const val KEY_SORT = "filter_sort"

        private const val KEY_QUERY = "filter_query"
    }

    fun saveFilters(filters: FiltersDTO) {
        if (filters.query.isNullOrEmpty()) {
            filters.query = null
        }

        prefs.edit {
            if (filters.year != null) {
                putString(KEY_YEAR, filters.year)
            } else {
                remove(KEY_YEAR)
            }

            if (filters.minRating != null && filters.minRating != 0f) {
                putFloat(KEY_MIN_RATING, filters.minRating!!)
            } else {
                remove(KEY_MIN_RATING)
            }

            if (!filters.platforms.isNullOrEmpty()) {
                putString(KEY_PLATFORMS, filters.platforms.joinToString(","))
            } else {
                remove(KEY_PLATFORMS)
            }

            if (filters.order != null) {
                putString(KEY_SORT, filters.order?.name)
            } else {
                remove(KEY_SORT)
            }

            if (!filters.query.isNullOrEmpty()) {
                putString(KEY_QUERY, filters.query)
            } else {
                remove(KEY_QUERY)
            }
        }
    }


    fun getFilters(): FiltersDTO {
        val year = prefs.getString(KEY_YEAR, null)
        val rating = prefs.getFloat(KEY_MIN_RATING, 0f)
        val platformsCSV = prefs.getString(KEY_PLATFORMS, "")
        val platforms = if (platformsCSV.isNullOrEmpty()) emptyList() else platformsCSV.split(",").mapNotNull { it.toIntOrNull() }
        val sort = prefs.getString(KEY_SORT, null)
        val sortOption = sort?.let { SortOption.valueOf(it) }
        val query = prefs.getString(KEY_QUERY, null)


        return FiltersDTO(
            year = year,
            minRating = rating,
            platforms = platforms,
            order = sortOption,
            query = query
        )
    }

    fun clearFilters() {
        prefs.edit {
            clear()
            apply()
        }
    }
}
