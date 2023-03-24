package com.example.usersanalytical.data.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM users_info WHERE name=:name ")
    suspend fun getName(name:String) : UsersDbModel?

}