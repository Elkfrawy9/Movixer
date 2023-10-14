package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.Cast

data class CastResponse(
    val id: Int,
    val cast: List<Cast>
)
