package com.zrifapps.game.domain.entity

data class Game(
    val id: Int,
    val name: String,
    val slug: String,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double,
    val ratingTop: Int,
    val ratings: List<Rating>,
    val platforms: List<PlatformWrapper>,
    val genres: List<Genre>,
    val stores: List<StoreWrapper>,
)


data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double,
)

data class PlatformWrapper(
    val platform: Platform,
)

data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
)

data class Genre(
    val id: Int,
    val name: String,
    val slug: String,
)

data class StoreWrapper(
    val store: Store,
)

data class Store(
    val id: Int,
    val name: String,
    val slug: String,
)
