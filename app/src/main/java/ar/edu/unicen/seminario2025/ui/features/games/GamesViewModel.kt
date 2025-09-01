package ar.edu.unicen.seminario2025.ui.features.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : ViewModel(){
    private val _games = MutableStateFlow<List<GameDTO>>(listOf())
    val games : StateFlow<List<GameDTO>> = _games.asStateFlow()
    private val _loading  = MutableStateFlow<Boolean>(true)
    val loading : StateFlow <Boolean> = _loading.asStateFlow()

    init {
        getGames()
    }

    fun getGames() {
        viewModelScope.launch {
            _games.value = gamesRepository.getGames()
            _loading.value = false
        }
    }
}