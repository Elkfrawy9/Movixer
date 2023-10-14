package com.elkfrawy.movixer.data.model

import com.elkfrawy.movixer.domain.model.People

data class PeopleResponse(
    val page: Int,
    val results: List<People>,

)
