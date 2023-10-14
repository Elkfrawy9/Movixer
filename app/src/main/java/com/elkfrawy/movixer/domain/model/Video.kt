package com.elkfrawy.movixer.domain.model

data class Video(
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String,
    val id: String,
)
