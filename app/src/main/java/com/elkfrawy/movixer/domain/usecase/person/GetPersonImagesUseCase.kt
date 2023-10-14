package com.elkfrawy.movixer.domain.usecase.person

import com.elkfrawy.movixer.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPersonImagesUseCase @Inject constructor(val repo: PeopleRepository) {

    suspend fun execute(personId: Int) = repo.getPersonImages(personId)

}