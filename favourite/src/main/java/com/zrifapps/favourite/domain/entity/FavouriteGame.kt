package com.zrifapps.favourite.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite_games")
data class FavouriteGame(
    @PrimaryKey val id: Int,
    val name: String,
    val slug: String,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double,
)

