package com.example.domain

import androidx.lifecycle.LiveData

class GetInfoAboutSingleCoin(private val repository: Repository) {
    suspend operator fun invoke(coinSym:String): LiveData<CoinPriceInfo> {
        return repository.getDetailInfoAboutSingleCoin(coinSym)
    }
}