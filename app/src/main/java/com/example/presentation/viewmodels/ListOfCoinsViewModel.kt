package com.example.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RepositoryImpl
import com.example.domain.GetTopCoinsUseCase
import com.example.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfCoinsViewModel @Inject constructor(
    private val getTopCoinsUseCase: GetTopCoinsUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {


    suspend fun getTopCoinsLD() = getTopCoinsUseCase()

    init {
        loadDataUseCase()
    }

}
