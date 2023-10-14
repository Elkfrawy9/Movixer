package com.elkfrawy.movixer.domain.model

data class PeopleDetail(
    val id: Int,
    val biography: String,
    val birthday: String,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String,
    val popularity: Float,
    val profile_path: String,
)
