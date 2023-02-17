package com.example.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun loadData()

    suspend fun getTopCoins(): LiveData<List<CoinPriceInfo>>

    suspend fun getDetailInfoAboutSingleCoin(coinSym:String): LiveData<CoinPriceInfo>

}