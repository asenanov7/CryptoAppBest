package com.example.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.RepositoryImpl
import com.example.domain.CoinPriceInfo
import com.example.domain.GetInfoAboutSingleCoinUseCase

class DetailViewModel(application: Application):AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getInfoAboutSingleCoinUseCase = GetInfoAboutSingleCoinUseCase(repository)

    suspend fun getInfoAboutSingleCoinLD(coinSym:String): LiveData<CoinPriceInfo> {
       return getInfoAboutSingleCoinUseCase(coinSym)
    }


}