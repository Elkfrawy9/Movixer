package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.Series

data class SeriesResponse(
    val page: Int,
    val results: List<Series>,
    val cast: List<Series>,
    val total_pages: Int,
    val total_results: Int,
)
