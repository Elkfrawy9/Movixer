package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.Review

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>
)
