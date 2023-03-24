package com.example.usersanalytical.domain

import com.example.usersanalytical.domain.model.Users

interface Repository {

    suspend fun getUserFromDb(name: String) : Users
}