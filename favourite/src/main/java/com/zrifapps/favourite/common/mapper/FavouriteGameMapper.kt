package com.zrifapps.favourite.common.mapper

import com.zrifapps.core.domain.model.CoreFavouriteGame
import com.zrifapps.favourite.domain.entity.FavouriteGame

fun CoreFavouriteGame.toFavouriteGame() = FavouriteGame(
    id = id,
    name = name,
    slug = slug,
    released = released,
    backgroundImage = backgroundImage,
    rating = rating,
)

