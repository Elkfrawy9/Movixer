package com.elkfrawy.movixer.domain.model

data class SeriesDetails(
    val adult: Boolean,
    val backdrop_path: String,
    val episode_run_time: List<Int>,
    val in_production: Boolean,
    val last_air_date: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val original_name: String,
    val overview: String,
    val popularity: Float,
    val seasons: List<Season>,
    val poster_path: String,
    val first_air_date: String,
    val tagline: String,
    val vote_average: Float,
    val vote_count: Int,
)
