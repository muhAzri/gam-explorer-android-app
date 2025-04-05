package com.zrifapps.favourite.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zrifapps.favourite.domain.entity.FavouriteGame
import com.zrifapps.favourite.presentation.components.tile.FavouriteGameListTile
import com.zrifapps.game.presentation.viewmodels.favourite.FavouriteGamesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteGamesListScreen(
    onGameClicked: (String) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: FavouriteGamesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favourite Games") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.error != null -> {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.favouriteGames.isEmpty() -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No favourite games yet",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Add some games to your favourites",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                else -> {
                    FavouriteGamesList(
                        games = uiState.favouriteGames,
                        onGameClicked = onGameClicked,
                        onRemoveFromFavourites = { gameId ->
                            viewModel.removeFromFavourites(gameId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FavouriteGamesList(
    games: List<FavouriteGame>,
    onGameClicked: (String) -> Unit,
    onRemoveFromFavourites: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = games.size,
            key = { index -> games[index].id }
        ) { index ->
            val game = games[index]
            FavouriteGameListTile(
                game = game,
                onClick = { onGameClicked(game.id.toString()) },
                onRemoveClick = { onRemoveFromFavourites(game.id) }
            )
        }
    }
}
