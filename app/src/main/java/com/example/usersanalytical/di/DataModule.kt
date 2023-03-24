package com.example.usersanalytical.di

import android.app.Application
import com.example.usersanalytical.data.database.AppDataBase
import com.example.usersanalytical.data.database.UsersDao
import com.example.usersanalytical.data.repository.RepositoryImpl
import com.example.usersanalytical.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindRepositoryImpl(impl: RepositoryImpl) : Repository

    companion object{

        @Provides
        @ApplicationScope
        fun provideUsersDao(application: Application) : UsersDao{
            return AppDataBase.getInstance(application).UsersDao()
        }
    }
}