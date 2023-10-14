package com.elkfrawy.movixer.domain.model

data class Cast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Float,
    val profile_path: String,
    val cast_id: Int,
    val character: String,
    val order: Int,

)
