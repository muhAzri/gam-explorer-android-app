package com.zrifapps.game.common.mapper

import com.zrifapps.game.data.dto.GenreDTO
import com.zrifapps.game.domain.entity.Genre

fun GenreDTO.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        slug = slug
    )
}
