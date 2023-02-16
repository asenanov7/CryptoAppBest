package com.example.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.data.RepositoryImpl
import com.example.domain.GetTopCoinsUseCase

class ListOfCoinsViewModel(application: Application):AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getTopCoinsUseCase = GetTopCoinsUseCase(repository)

    suspend fun getTopCoinsLD() = getTopCoinsUseCase()

}
