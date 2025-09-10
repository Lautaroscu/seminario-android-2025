package ar.edu.unicen.seminario2025.ui.features.games

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import ar.edu.unicen.seminario2025.ddl.models.games.GameDetailsDTO
import ar.edu.unicen.seminario2025.ddl.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : ViewModel(){
    private val _game = MutableStateFlow<GameDetailsDTO?>(
        null
    )
    val game = _game.asStateFlow()
    private val _loading  = MutableStateFlow<Boolean>(false)
    val loading : StateFlow <Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<Boolean>(false)
    val error : StateFlow<Boolean> = _error.asStateFlow()


    fun getGame(gameId : Int) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = gamesRepository.getGame(gameId)) {
                is ApiResult.Success -> _game.value  = result.data
                is ApiResult.Error -> _error.value = result.exception.message != null
            }
            _loading.value = false
        }

    }
}