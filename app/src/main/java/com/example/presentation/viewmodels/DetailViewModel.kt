package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CoinPriceInfo
import com.example.domain.GetInfoAboutSingleCoinUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getInfoAboutSingleCoinUseCase: GetInfoAboutSingleCoinUseCase
): ViewModel() {

    suspend fun getInfoAboutSingleCoinLD(coinSym:String): Flow<CoinPriceInfo> {
       return getInfoAboutSingleCoinUseCase(coinSym)
           .shareIn(viewModelScope, SharingStarted.Eagerly, 1)
    }


}