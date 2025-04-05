package com.zrifapps.game.common.mapper

import com.zrifapps.game.data.dto.RatingDTO
import com.zrifapps.game.domain.entity.Rating

fun RatingDTO.toDomain(): Rating {
    return Rating(
        id = id,
        title = title,
        count = count,
        percent = percent
    )
}
