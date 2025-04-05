package com.zrifapps.game.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.domain.entity.Genre
import com.zrifapps.game.domain.entity.Platform
import com.zrifapps.game.domain.entity.PlatformWrapper
import com.zrifapps.game.domain.entity.Rating
import com.zrifapps.game.domain.entity.Store
import com.zrifapps.game.domain.entity.StoreWrapper
import com.zrifapps.game.presentation.components.search_bar.SearchBar
import com.zrifapps.game.presentation.components.tile.GameListTile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListingScreen(
    onGameClicked: (Game) -> Unit,
    onFavoritesClicked: () -> Unit,
) {
    val mockGames = remember {
        listOf(
            Game(
                id = 1,
                name = "The Witcher 3: Wild Hunt",
                slug = "the-witcher-3-wild-hunt",
                released = "2015-05-18",
                backgroundImage = "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
                rating = 4.7,
                ratingTop = 5,
                ratings = listOf(
                    Rating(1, "Exceptional", 1500, 75.0),
                    Rating(2, "Recommended", 400, 20.0),
                    Rating(3, "Meh", 80, 4.0),
                    Rating(4, "Skip", 20, 1.0)
                ),
                platforms = listOf(
                    PlatformWrapper(Platform(1, "PC", "pc")),
                    PlatformWrapper(Platform(2, "PlayStation 4", "playstation4")),
                    PlatformWrapper(Platform(3, "Xbox One", "xbox-one")),
                    PlatformWrapper(Platform(4, "Nintendo Switch", "nintendo-switch"))
                ),
                genres = listOf(
                    Genre(1, "RPG", "rpg"),
                    Genre(2, "Action", "action"),
                    Genre(3, "Adventure", "adventure")
                ),
                stores = listOf(
                    StoreWrapper(Store(1, "Steam", "steam")),
                    StoreWrapper(Store(2, "GOG", "gog")),
                    StoreWrapper(Store(3, "PlayStation Store", "playstation-store"))
                )
            ),
            Game(
                id = 2,
                name = "Red Dead Redemption 2",
                slug = "red-dead-redemption-2",
                released = "2018-10-26",
                backgroundImage = "https://media.rawg.io/media/games/511/5118aff5091cb3efec399c808f8c598f.jpg",
                rating = 4.8,
                ratingTop = 5,
                ratings = listOf(
                    Rating(1, "Exceptional", 1800, 82.0),
                    Rating(2, "Recommended", 300, 14.0),
                    Rating(3, "Meh", 70, 3.0),
                    Rating(4, "Skip", 30, 1.0)
                ),
                platforms = listOf(
                    PlatformWrapper(Platform(1, "PC", "pc")),
                    PlatformWrapper(Platform(2, "PlayStation 4", "playstation4")),
                    PlatformWrapper(Platform(3, "Xbox One", "xbox-one"))
                ),
                genres = listOf(
                    Genre(1, "Action", "action"),
                    Genre(2, "Adventure", "adventure"),
                    Genre(3, "RPG", "rpg")
                ),
                stores = listOf(
                    StoreWrapper(Store(1, "Steam", "steam")),
                    StoreWrapper(Store(2, "Epic Games", "epic-games")),
                    StoreWrapper(Store(3, "PlayStation Store", "playstation-store"))
                )
            ),
            Game(
                id = 3,
                name = "Elden Ring",
                slug = "elden-ring",
                released = "2022-02-25",
                backgroundImage = "https://media.rawg.io/media/games/5ec/5ecac5cb026ec26a56efcc546364e348.jpg",
                rating = 4.9,
                ratingTop = 5,
                ratings = listOf(
                    Rating(1, "Exceptional", 2000, 88.0),
                    Rating(2, "Recommended", 200, 9.0),
                    Rating(3, "Meh", 50, 2.0),
                    Rating(4, "Skip", 20, 1.0)
                ),
                platforms = listOf(
                    PlatformWrapper(Platform(1, "PC", "pc")),
                    PlatformWrapper(Platform(2, "PlayStation 5", "playstation5")),
                    PlatformWrapper(Platform(3, "Xbox Series X", "xbox-series-x"))
                ),
                genres = listOf(
                    Genre(1, "RPG", "rpg"),
                    Genre(2, "Action", "action"),
                    Genre(3, "Adventure", "adventure")
                ),
                stores = listOf(
                    StoreWrapper(Store(1, "Steam", "steam")),
                    StoreWrapper(Store(2, "PlayStation Store", "playstation-store")),
                    StoreWrapper(Store(3, "Xbox Store", "xbox-store"))
                )
            ),
            Game(
                id = 4,
                name = "God of War Ragnarök",
                slug = "god-of-war-ragnarok",
                released = "2022-11-09",
                backgroundImage = "https://media.rawg.io/media/games/1c3/1c31540e34d4f9965870e0605d60c3da.jpg",
                rating = 4.8,
                ratingTop = 5,
                ratings = listOf(
                    Rating(1, "Exceptional", 1700, 80.0),
                    Rating(2, "Recommended", 350, 16.0),
                    Rating(3, "Meh", 60, 3.0),
                    Rating(4, "Skip", 20, 1.0)
                ),
                platforms = listOf(
                    PlatformWrapper(Platform(1, "PlayStation 5", "playstation5")),
                    PlatformWrapper(Platform(2, "PlayStation 4", "playstation4"))
                ),
                genres = listOf(
                    Genre(1, "Action", "action"),
                    Genre(2, "Adventure", "adventure")
                ),
                stores = listOf(
                    StoreWrapper(Store(1, "PlayStation Store", "playstation-store"))
                )
            ),
            Game(
                id = 5,
                name = "Cyberpunk 2077",
                slug = "cyberpunk-2077",
                released = "2020-12-10",
                backgroundImage = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
                rating = 4.2,
                ratingTop = 5,
                ratings = listOf(
                    Rating(1, "Exceptional", 1200, 60.0),
                    Rating(2, "Recommended", 450, 22.0),
                    Rating(3, "Meh", 250, 13.0),
                    Rating(4, "Skip", 100, 5.0)
                ),
                platforms = listOf(
                    PlatformWrapper(Platform(1, "PC", "pc")),
                    PlatformWrapper(Platform(2, "PlayStation 4", "playstation4")),
                    PlatformWrapper(Platform(3, "PlayStation 5", "playstation5")),
                    PlatformWrapper(Platform(4, "Xbox One", "xbox-one")),
                    PlatformWrapper(Platform(5, "Xbox Series X", "xbox-series-x"))
                ),
                genres = listOf(
                    Genre(1, "RPG", "rpg"),
                    Genre(2, "Action", "action"),
                    Genre(3, "Adventure", "adventure")
                ),
                stores = listOf(
                    StoreWrapper(Store(1, "Steam", "steam")),
                    StoreWrapper(Store(2, "GOG", "gog")),
                    StoreWrapper(Store(3, "Epic Games", "epic-games"))
                )
            )
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    var filteredGames by remember { mutableStateOf(mockGames) }

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
                    filteredGames = if (query.isBlank()) {
                        mockGames
                    } else {
                        mockGames.filter {
                            it.name.contains(query, ignoreCase = true)
                        }
                    }
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredGames) { game ->
                    GameListTile(
                        game = game,
                        onClick = { onGameClicked(game) }
                    )
                }
            }
        }
    }
}


