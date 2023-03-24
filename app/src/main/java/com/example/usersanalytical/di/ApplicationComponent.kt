package com.example.usersanalytical.di

import android.app.Application
import com.example.usersanalytical.ui.MainActivity
import com.example.usersanalytical.ui.SecondActivity
import com.example.usersanalytical.ui.app.App
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: SecondActivity)

    fun inject(application: App)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application) : ApplicationComponent
    }
}