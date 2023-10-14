package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.Movie

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val cast: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
)
