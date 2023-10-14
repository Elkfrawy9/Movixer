package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.Image

data class ImageResponse(
    val id: Int,
    val profiles: List<Image>
)
