package com.example.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.RepositoryImpl
import com.example.domain.CoinPriceInfo
import com.example.domain.GetInfoAboutSingleCoin

class DetailViewModel(application: Application):AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getInfoAboutSingleCoin = GetInfoAboutSingleCoin(repository)

    suspend fun getInfoAboutSingleCoinLD(coinSym:String): LiveData<CoinPriceInfo> {
       return getInfoAboutSingleCoin(coinSym)
    }


}