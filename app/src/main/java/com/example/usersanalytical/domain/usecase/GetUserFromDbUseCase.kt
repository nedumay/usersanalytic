package com.example.usersanalytical.domain.usecase

import com.example.usersanalytical.domain.Repository
import javax.inject.Inject

class GetUserFromDbUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(name:String) = repository.getUserFromDb(name)
}