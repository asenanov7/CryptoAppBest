package com.example.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetTopCoinsUseCase
import com.example.domain.LoadDataUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ListOfCoinsViewModel @Inject constructor(
    private val getTopCoinsUseCase: GetTopCoinsUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {


    suspend fun getTopCoinsLD() = getTopCoinsUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        loadDataUseCase()
    }

}
