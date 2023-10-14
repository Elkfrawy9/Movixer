package com.elkfrawy.movixer.domain.model

data class Series(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val vote_average: Float,
    val vote_count: Int,
)