package com.example.domain

import androidx.lifecycle.LiveData
import com.example.domain.entity.CoinPriceInfo
import com.example.domain.entity.CoinsResponse
import com.example.domain.entity.DetailOfCoinsResponse

interface Repository {

    suspend fun getTopCoins(): LiveData<List<CoinPriceInfo>>

}