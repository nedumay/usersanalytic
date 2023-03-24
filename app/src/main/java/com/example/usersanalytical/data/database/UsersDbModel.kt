package com.example.usersanalytical.data.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_info")
data class UsersDbModel(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo("id")
    val id: Int,
    @NonNull
    @ColumnInfo("name")
    val name: String,
    @NonNull
    @ColumnInfo("year_old")
    val year_old: String,
    @NonNull
    @ColumnInfo("hemoglobin")
    val hemoglobin: String,
    @NonNull
    @ColumnInfo("erythrocytes")
    val erythrocytes: String,
    @NonNull
    @ColumnInfo("platelets")
    val platelets: String,
    @NonNull
    @ColumnInfo("lymphocytes")
    val lymphocytes: String,
    @NonNull
    @ColumnInfo("monocytes")
    val monocytes: String,
    @NonNull
    @ColumnInfo("basophils")
    val basophils: String
    )
