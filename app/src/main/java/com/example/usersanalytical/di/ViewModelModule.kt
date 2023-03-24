package com.example.usersanalytical.di

import androidx.lifecycle.ViewModel
import com.example.usersanalytical.ui.MainViewModel
import com.example.usersanalytical.ui.SecondViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    fun bindSecondViewModel(viewModel: SecondViewModel) : ViewModel
}