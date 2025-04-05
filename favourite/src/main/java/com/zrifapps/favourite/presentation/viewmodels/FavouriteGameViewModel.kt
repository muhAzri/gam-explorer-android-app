package com.zrifapps.game.presentation.viewmodels.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrifapps.favourite.domain.entity.FavouriteGame
import com.zrifapps.favourite.domain.repository.FavouriteGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteGamesViewModel @Inject constructor(
    private val favouriteGameRepository: FavouriteGameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteGamesUiState())
    val uiState: StateFlow<FavouriteGamesUiState> = _uiState

    init {
        loadFavouriteGames()
    }

    private fun loadFavouriteGames() {
        _uiState.update { it.copy(isLoading = true) }

        favouriteGameRepository.getAll()
            .onEach { games ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        favouriteGames = games,
                        error = null
                    )
                }
            }
            .catch { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Unknown error occurred"
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun removeFromFavourites(gameId: Int) {
        viewModelScope.launch {
            try {
                // Find the game in the current list
                val gameToRemove = _uiState.value.favouriteGames.find { it.id == gameId }

                // Remove the game if found
                if (gameToRemove != null) {
                    favouriteGameRepository.removeFromFavourites(gameToRemove)
                    // The UI will be updated automatically via the Flow from getAll()
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.localizedMessage ?: "Failed to remove game from favourites")
                }
            }
        }
    }
}

data class FavouriteGamesUiState(
    val isLoading: Boolean = false,
    val favouriteGames: List<FavouriteGame> = emptyList(),
    val error: String? = null
)
