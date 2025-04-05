package com.zrifapps.game.presentation.viewmodels.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zrifapps.game.data.paging.GamePagingSource
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val gameRepository: GameRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val games: Flow<PagingData<Game>> = _searchQuery.flatMapLatest { query ->
        getGames(query)
    }.cachedIn(viewModelScope).shareIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), replay = 1
    )

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun getGames(query: String): Flow<PagingData<Game>> {
        return Pager(config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            prefetchDistance = PREFETCH_DISTANCE
        ), pagingSourceFactory = {
            GamePagingSource(gameRepository, query)
        }).flow
    }


    companion object {
        private const val PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE = 5
    }
}
