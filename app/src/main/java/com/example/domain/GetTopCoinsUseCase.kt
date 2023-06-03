package com.example.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopCoinsUseCase @Inject constructor (private val repository: Repository){
    suspend operator fun invoke(): Flow<List<CoinPriceInfo>> {
        return repository.getTopCoins()
    }
}