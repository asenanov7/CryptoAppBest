package com.example.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RepositoryImpl
import com.example.domain.GetTopCoinsUseCase
import com.example.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class ListOfCoinsViewModel(application: Application):AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val getTopCoinsUseCase = GetTopCoinsUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    suspend fun getTopCoinsLD() = getTopCoinsUseCase()

    init {
            loadDataUseCase()
    }

}
