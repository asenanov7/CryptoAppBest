package com.example.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInfoAboutSingleCoinUseCase @Inject constructor (private val repository: Repository) {
    suspend operator fun invoke(coinSym:String): Flow<CoinPriceInfo> {
        return repository.getDetailInfoAboutSingleCoin(coinSym)
    }
}