package com.example.domain

import androidx.lifecycle.LiveData
import com.example.domain.entity.CoinPriceInfo

class GetInfoAboutSingleCoin(private val repository: Repository) {
    suspend operator fun invoke(coinSym:String): LiveData<CoinPriceInfo> {
        return repository.getDetailInfoAboutSingleCoin(coinSym)
    }
}