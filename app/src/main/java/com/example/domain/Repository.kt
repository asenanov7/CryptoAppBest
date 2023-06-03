package com.example.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun loadData()

    suspend fun getTopCoins(): Flow<List<CoinPriceInfo>>

    suspend fun getDetailInfoAboutSingleCoin(coinSym:String): Flow<CoinPriceInfo>

}