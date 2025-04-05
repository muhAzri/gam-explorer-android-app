package com.zrifapps.game.common.mapper

import com.zrifapps.game.data.dto.PlatformDTO
import com.zrifapps.game.domain.entity.Platform

fun PlatformDTO.toDomain(): Platform {
    return Platform(
        id = id,
        name = name,
        slug = slug
    )
}
