package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.Video

data class VideoResponse(
    val id: Int,
    val results: List<Video>
)
