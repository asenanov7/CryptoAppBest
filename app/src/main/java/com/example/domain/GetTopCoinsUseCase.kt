package com.example.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetTopCoinsUseCase @Inject constructor (private val repository: Repository){
    suspend operator fun invoke(): LiveData<List<CoinPriceInfo>> {
        return repository.getTopCoins()
    }
}