package com.zrifapps.game.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zrifapps.game.domain.entity.Game
import com.zrifapps.game.domain.entity.Genre
import com.zrifapps.game.domain.entity.Platform
import com.zrifapps.game.domain.entity.PlatformWrapper
import com.zrifapps.game.domain.entity.Rating
import com.zrifapps.game.domain.entity.Store
import com.zrifapps.game.domain.entity.StoreWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    gameId: String,
    onBackPressed: () -> Unit,
) {
    var isFavorite by remember { mutableStateOf(false) }

    val game = Game(
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
            Genre(1, "RPG", "rpg"), Genre(2, "Action", "action"), Genre(3, "Adventure", "adventure")
        ),
        stores = listOf(
            StoreWrapper(Store(1, "Steam", "steam")),
            StoreWrapper(Store(2, "GOG", "gog")),
            StoreWrapper(Store(3, "PlayStation Store", "playstation-store"))
        )
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text(game.name) }, navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }, actions = {
            IconButton(onClick = { isFavorite = !isFavorite }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites"
                )
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GameDetailContent(game = game)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameDetailContent(game: Game) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Game Hero Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(game.backgroundImage)
                    .crossfade(true).build(),
                contentDescription = "${game.name} cover image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Rating badge
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f),
                shadowElevation = 4.dp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "${game.rating} / ${game.ratingTop}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(bottom = 2.dp)
                    )
                }
            }


        }

        // Game Details
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Title and Release Date
            Text(
                text = game.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            game.released?.let {
                Text(
                    text = "Released: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Platforms
            game.platforms?.let { platforms ->
                Text(
                    text = "Available on:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    platforms.forEach { platformWrapper ->
                        PlatformChip(platformWrapper)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Genres
            game.genres?.let { genres ->
                Text(
                    text = "Genres:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    genres.forEach { genre ->
                        GenreChip(genre)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Ratings breakdown
            game.ratings?.let { ratings ->
                Text(
                    text = "Rating Breakdown:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ratings.sortedByDescending { it.percent }.forEach { rating ->
                        RatingBar(rating = rating)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Stores
            game.stores?.let { stores ->
                Text(
                    text = "Available at:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    stores.forEach { storeWrapper ->
                        StoreChip(storeWrapper)
                    }
                }
            }
        }
    }
}

@Composable
fun PlatformChip(platformWrapper: PlatformWrapper) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = platformWrapper.platform.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun GenreChip(genre: Genre) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun StoreChip(storeWrapper: StoreWrapper) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = storeWrapper.store.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun RatingBar(rating: Rating) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = rating.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.width(100.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(rating.percent.toFloat() / 100f)
                        .height(12.dp)
                        .background(
                            when {
                                rating.title.equals("exceptional", ignoreCase = true) -> Color(
                                    0xFF66BB6A
                                )

                                rating.title.equals("recommended", ignoreCase = true) -> Color(
                                    0xFF42A5F5
                                )

                                rating.title.equals("meh", ignoreCase = true) -> Color(0xFFFFA726)
                                else -> Color(0xFFEF5350)
                            }
                        )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${rating.percent.toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.width(40.dp),
                textAlign = TextAlign.End
            )
        }

        Text(
            text = "${rating.count} votes",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 108.dp)
        )
    }
}
