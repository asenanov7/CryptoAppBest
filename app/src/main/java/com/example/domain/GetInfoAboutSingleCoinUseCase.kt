package com.example.domain

import androidx.lifecycle.LiveData

class GetInfoAboutSingleCoinUseCase(private val repository: Repository) {
    suspend operator fun invoke(coinSym:String): LiveData<CoinPriceInfo> {
        return repository.getDetailInfoAboutSingleCoin(coinSym)
    }
}