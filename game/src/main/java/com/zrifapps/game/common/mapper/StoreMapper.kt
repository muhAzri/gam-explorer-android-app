package com.zrifapps.game.common.mapper

import com.zrifapps.game.data.dto.StoreDTO
import com.zrifapps.game.domain.entity.Store

fun StoreDTO.toDomain(): Store {
    return Store(
        id = id,
        name = name,
        slug = slug
    )
}
