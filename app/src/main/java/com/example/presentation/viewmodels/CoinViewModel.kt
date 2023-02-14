package com.example.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RepositoryImpl
import com.example.domain.GetTopCoinsUseCase
import com.example.domain.Repository
import kotlinx.coroutines.launch

class CoinViewModel(application: Application):AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getTopCoinsUseCase = GetTopCoinsUseCase(repository)

    suspend fun getTopCoinsLD() = getTopCoinsUseCase()

}
