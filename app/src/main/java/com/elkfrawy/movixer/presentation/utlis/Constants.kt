package com.elkfrawy.movixer.presentation.utlis

import com.elkfrawy.movixer.domain.model.Genre


const val NOW_PLAYING_LIST: Int = 1
const val POPULAR_LIST: Int = 2
const val UP_COMING_LIST: Int = 3
const val TOP_RATED_LIST: Int = 4

const val AIRING_TODAY_LIST: Int = 10
const val SERIES_POPULAR_LIST: Int = 20
const val ON_THE_AIR_LIST: Int = 30
const val SERIES_TOP_RATED_LIST: Int = 40

const val SERIES_ADAPTER: String = "SERIES_DATA_ID"
const val MOVIES_ADAPTER: String = "MOVIES_DATA_ID"

const val SEARCH_PAGER_POSITION = "position_key"
const val SEARCH_TEXT_KEY = "text_key"

const val SERIES_REVIEW_TYPE: Int = 112
const val MOVIE_REVIEW_TYPE: Int = 113

val genreMovie: List<Genre> = listOf(
    Genre(28, "Action"),
    Genre(12, "Adventure"),
    Genre(16, "Animation"),
    Genre(35, "Comedy"),
    Genre(80, "Crime"),
    Genre(99, "Documentary"),
    Genre(18, "Drama"),
    Genre(10751, "Family"),
    Genre(14, "Fantasy"),
    Genre(36, "History"),
    Genre(27, "Horror"),
    Genre(10402, "Music"),
    Genre(9648, "Mystery"),
    Genre(10749, "Romance"),
    Genre(878, "Science Fiction"),
    Genre(10770, "TV Movie"),
    Genre(53, "Thriller"),
    Genre(10752, "War"),
    Genre(37, "Western"),
)

val genreTV: List<Genre> = listOf(
    Genre(10759, "Action & Adventure"),
    Genre(16, "Animation"),
    Genre(35, "Comedy"),
    Genre(80, "Crime"),
    Genre(99, "Documentary"),
    Genre(18, "Drama"),
    Genre(10751, "Family"),
    Genre(9648, "Mystery"),
    Genre(10762, "Kids"),
    Genre(10763, "News"),
    Genre(10764, "Reality"),
    Genre(10765, "Sci-Fi & Fantasy"),
    Genre(10766, "Soap"),
    Genre(10767, "Talk"),
    Genre(10768, "War & Politics"),
    Genre(37, "Western"),
)