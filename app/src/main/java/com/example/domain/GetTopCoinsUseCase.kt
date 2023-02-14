package com.example.domain

import androidx.lifecycle.LiveData
import com.example.domain.entity.CoinPriceInfo
import com.example.domain.entity.CoinsResponse

class GetTopCoinsUseCase(private val repository: Repository){
    suspend operator fun invoke(): LiveData<List<CoinPriceInfo>> {
        return repository.getTopCoins()
    }
}