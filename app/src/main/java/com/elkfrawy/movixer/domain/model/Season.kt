package com.elkfrawy.movixer.domain.model

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Float,
)
