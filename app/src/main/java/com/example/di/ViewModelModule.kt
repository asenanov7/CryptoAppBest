package com.example.di

import androidx.lifecycle.ViewModel
import com.example.presentation.viewmodels.DetailViewModel
import com.example.presentation.viewmodels.ListOfCoinsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @Binds
    fun bindDetailViewModel(impl: DetailViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ListOfCoinsViewModel::class)
    @Binds
    fun bindListOfCoinsViewModel(impl: ListOfCoinsViewModel): ViewModel

}