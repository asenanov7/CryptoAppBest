package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.CoinPriceInfo
import com.example.domain.GetInfoAboutSingleCoinUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getInfoAboutSingleCoinUseCase: GetInfoAboutSingleCoinUseCase
): ViewModel() {


    suspend fun getInfoAboutSingleCoinLD(coinSym:String): LiveData<CoinPriceInfo> {
       return getInfoAboutSingleCoinUseCase(coinSym)
    }


}