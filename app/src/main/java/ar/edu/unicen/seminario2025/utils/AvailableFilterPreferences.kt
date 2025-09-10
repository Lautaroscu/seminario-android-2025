package ar.edu.unicen.seminario2025.utils

import android.content.Context
import android.content.SharedPreferences
import jakarta.inject.Inject
import jakarta.inject.Singleton
import androidx.core.content.edit
import ar.edu.unicen.seminario2025.ddl.models.games.PlatformDTO
import com.google.gson.Gson

@Singleton
class AvailableFiltersPreferences @Inject constructor(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("available_filters", Context.MODE_PRIVATE)

    fun savePlatforms(platforms: Set<PlatformDTO>) {
        val json = Gson().toJson(platforms)

        prefs.edit { putString("platforms", json).apply() }
    }

    fun getPlatforms(): Set<PlatformDTO> = prefs.getString("platforms", "[]").let {
        if (it.isNullOrEmpty()) {
            emptySet()
        } else {
            Gson().fromJson(it, Array<PlatformDTO>::class.java).toSet()
        }
    }

    fun saveYears(years: Set<String>) {
        prefs.edit { putStringSet("years", years) }
    }

    fun getYears(): Set<String> = prefs.getStringSet("years", emptySet()) ?: emptySet()
}
