package com.zrifapps.game.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.presentation.components.search_bar.SearchBar
import com.zrifapps.game.presentation.components.tile.GameListTile
import com.zrifapps.game.presentation.viewmodels.list.GameListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    onGameClicked: (Game) -> Unit,
    onFavoritesClicked: () -> Unit,
    viewModel: GameListViewModel = hiltViewModel()
) {
    val games = viewModel.games.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Explorer") },
                actions = {
                    IconButton(onClick = onFavoritesClicked) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorites"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { query ->
                    searchQuery = query
                    viewModel.setSearchQuery(query)
                }
            )

            GameList(
                games = games,
                onGameClicked = onGameClicked
            )
        }
    }
}

@Composable
fun GameList(
    games: LazyPagingItems<Game>,
    onGameClicked: (Game) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Show loading for initial load
        if (games.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        // Show error for initial load
        else if (games.loadState.refresh is LoadState.Error) {
            val error = (games.loadState.refresh as LoadState.Error).error
            Text(
                text = "Error: ${error.localizedMessage}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        // Show content when data is available
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = games.itemCount,
                    key = games.itemKey { game -> game.id }
                ) { index ->
                    val game = games[index]
                    if (game != null) {
                        GameListTile(
                            game = game,
                            onClick = { onGameClicked(game) }
                        )
                    }
                }

                // Show loading for append (pagination)
                if (games.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                // Show error for append (pagination)
                if (games.loadState.append is LoadState.Error) {
                    item {
                        val error = (games.loadState.append as LoadState.Error).error
                        Text(
                            text = "Error: ${error.localizedMessage}",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
