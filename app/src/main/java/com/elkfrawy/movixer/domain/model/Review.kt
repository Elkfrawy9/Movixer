package com.elkfrawy.movixer.domain.model

data class Review(
    val author: String,
    val content: String,
    val author_details: Author,
    val created_at: String,
    val id: String,
    val updated_at: String,

)
