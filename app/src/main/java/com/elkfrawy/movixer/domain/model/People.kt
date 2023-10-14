package com.elkfrawy.movixer.domain.model

data class People(
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Float,
    val profile_path: String,

)
