package com.example.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetInfoAboutSingleCoinUseCase @Inject constructor (private val repository: Repository) {
    suspend operator fun invoke(coinSym:String): LiveData<CoinPriceInfo> {
        return repository.getDetailInfoAboutSingleCoin(coinSym)
    }
}