package com.example.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.GetTopCoinsUseCase
import com.example.domain.LoadDataUseCase
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
