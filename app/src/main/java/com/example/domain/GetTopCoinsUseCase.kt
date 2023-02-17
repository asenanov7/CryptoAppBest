package com.example.domain

import androidx.lifecycle.LiveData

class GetTopCoinsUseCase(private val repository: Repository){
    suspend operator fun invoke(): LiveData<List<CoinPriceInfo>> {
        return repository.getTopCoins()
    }
}