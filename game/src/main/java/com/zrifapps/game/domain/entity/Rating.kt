package com.zrifapps.game.domain.entity

data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double,
)
