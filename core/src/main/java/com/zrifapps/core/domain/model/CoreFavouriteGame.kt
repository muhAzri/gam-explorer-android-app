package com.zrifapps.core.domain.model

data class CoreFavouriteGame(
    val id: Int,
    val name: String,
    val slug: String,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double,
)
