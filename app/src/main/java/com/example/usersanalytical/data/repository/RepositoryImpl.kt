package com.example.usersanalytical.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.usersanalytical.data.database.UsersDao
import com.example.usersanalytical.data.mapper.Mapper
import com.example.usersanalytical.domain.Repository
import com.example.usersanalytical.domain.model.Users
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val mapper: Mapper
) : Repository {
    override suspend fun getUserFromDb(name: String): Users {
        val dbModel = usersDao.getName(name)
        Log.d("getUser","UserDb: $dbModel")
        return mapper.mapDbModelToEntity(dbModel)
    }
}