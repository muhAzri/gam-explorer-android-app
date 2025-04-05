package com.zrifapps.game.presentation.viewmodels.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrifapps.core.common.manager.FavouriteGameManager
import com.zrifapps.core.domain.model.CoreFavouriteGame
import com.zrifapps.core.network.NetworkResult
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val favouriteGameManager: FavouriteGameManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameDetailUiState())
    val uiState: StateFlow<GameDetailUiState> = _uiState.asStateFlow()

    fun loadGameDetail(gameId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            when (val result = gameRepository.getGameDetail(gameId)) {
                is NetworkResult.Success -> {
                    val game = result.data
                    _uiState.update {
                        it.copy(
                            game = game,
                            isLoading = false,
                            error = null
                        )
                    }
                    checkFavoriteStatus(game.id)
                }

                is NetworkResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    private fun checkFavoriteStatus(gameId: Int) {
        viewModelScope.launch {
            val isFavorite = favouriteGameManager.isFavourite(gameId)
            _uiState.update { it.copy(isFavorite = isFavorite) }
        }
    }

    fun toggleFavorite() {
        val currentGame = _uiState.value.game ?: return
        val isFavorite = _uiState.value.isFavorite

        viewModelScope.launch {
            val coreFavoriteGame = CoreFavouriteGame(
                id = currentGame.id,
                name = currentGame.name,
                backgroundImage = currentGame.backgroundImage ?: "",
                rating = currentGame.rating,
                slug = currentGame.slug,
                released = currentGame.released
            )

            if (isFavorite) {
                favouriteGameManager.removeFromFavourites(coreFavoriteGame)
            } else {
                favouriteGameManager.addToFavourites(coreFavoriteGame)
            }

            _uiState.update { it.copy(isFavorite = !isFavorite) }
        }
    }
}

data class GameDetailUiState(
    val game: Game? = null,
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val error: String? = null,
)
