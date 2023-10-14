package com.elkfrawy.movixer.domain.usecase.search

import com.elkfrawy.movixer.domain.repository.SearchRepository
import javax.inject.Inject

class GetSearchedPeopleUseCase @Inject constructor(val searchRepo: SearchRepository) {

    fun execute(text: String) = searchRepo.peopleSearch(text)

}