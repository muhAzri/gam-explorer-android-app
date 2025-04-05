package com.zrifapps.game.common.mapper

import com.zrifapps.game.data.dto.StoreWrapperDTO
import com.zrifapps.game.domain.entity.StoreWrapper

fun StoreWrapperDTO.toDomain(): StoreWrapper {
    return StoreWrapper(
        store = store.toDomain()
    )
}
