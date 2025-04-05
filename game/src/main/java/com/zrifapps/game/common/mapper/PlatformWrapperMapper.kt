package com.zrifapps.game.common.mapper

import com.zrifapps.game.data.dto.PlatformWrapperDTO
import com.zrifapps.game.domain.entity.PlatformWrapper

fun PlatformWrapperDTO.toDomain(): PlatformWrapper {
    return PlatformWrapper(
        platform = platform.toDomain()
    )
}

