package ar.edu.unicen.seminario2025.ui.features.games

import FiltersDTO
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.PlatformDTO
import ar.edu.unicen.seminario2025.ddl.repository.GamesRepository
import ar.edu.unicen.seminario2025.ui.features.games.enums.SortOption
import ar.edu.unicen.seminario2025.utils.AvailableFiltersPreferences
import ar.edu.unicen.seminario2025.utils.FiltersPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesRepository: GamesRepository ,
    private val filtersPreferences: FiltersPreferences,
    private val availableFiltersPreferences: AvailableFiltersPreferences
) : ViewModel() {


    private val _games = MutableStateFlow<List<GameDTO>>(listOf())
    val games: StateFlow<List<GameDTO>> = _games.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _years = MutableStateFlow<List<Int>>(emptyList())
    val years: StateFlow<List<Int>> = _years
    private val _platforms = MutableStateFlow<List<Pair<Int, String>>>(emptyList())
    val platforms: StateFlow<List<Pair<Int, String>>> = _platforms

    private val _savedFilters = MutableStateFlow<FiltersDTO>(filtersPreferences.getFilters())
     val savedFilters: StateFlow<FiltersDTO> = _savedFilters
    private val _query = MutableStateFlow<String>(savedFilters.value.query ?: "")
    val query: StateFlow<String> = _query

    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error





    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun onApplyFilters(filters: FiltersDTO = savedFilters.value) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = gamesRepository.getGames(filters)) {
                is ApiResult.Success -> {
                    _games.value = result.data
                    _error.value = null
                }
                is ApiResult.Error -> _error.value = result.error
            }
            _loading.value = false
            filtersPreferences.saveFilters(filters)
            _savedFilters.value = filters
            updateFiltersFromGames()

        }
    }

    init {
        getGames()
    }

    @OptIn(FlowPreview::class)
    fun getGames() {
        viewModelScope.launch {
            _query
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    val filters = _savedFilters.value.copy(query = query)
                    onApplyFilters(filters)
                }
        }
    }



    private fun getPlatformsToFilter(): List<Pair<Int, String>> {
        val savedPlatforms = availableFiltersPreferences.getPlatforms()
        val platforms = mutableSetOf<Pair<Int, String>>()

        if(savedPlatforms.isEmpty()) {
            games.value.forEach { game ->
                game.platforms?.forEach { wrapper ->
                    val id = wrapper.platform.id
                    val name = wrapper.platform.name
                    platforms.add(id to name)
                }
            }
            availableFiltersPreferences.savePlatforms(platforms.map { PlatformDTO(it.first, it.second , it.second) }.toSet())
        }else {
            platforms.addAll(savedPlatforms.map { it.id to it.name })
        }

        return platforms.toList()
    }


    private fun getYearsToFilter(): List<Int> {
        val years = mutableSetOf<Int>()
       for(i in 2000 .. 2024) {
           years.add(i)
        }
        return years.toSortedSet().toList()
    }
    private fun updateFiltersFromGames() {
        _platforms.value = getPlatformsToFilter()
        _years.value = getYearsToFilter()
    }

}

