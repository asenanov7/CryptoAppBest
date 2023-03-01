package com.example.data.database

import com.example.domain.CoinPriceInfo
import javax.inject.Inject

class MapperDB @Inject constructor(){

    fun mapEntityToDbModel(coinPriceInfo: CoinPriceInfo): DbModelCoinPriceInfo {
        return DbModelCoinPriceInfo(
            coinPriceInfo.fromSymbol,
            coinPriceInfo.toSymbol,
            coinPriceInfo.price,
            coinPriceInfo.lastUpdate,
            coinPriceInfo.highDay,
            coinPriceInfo.lowDay,
            coinPriceInfo.lastMarket,
            coinPriceInfo.imageUrl
        )
    }

    fun mapDbModelToEntity(dbModelCoinPriceInfo: DbModelCoinPriceInfo): CoinPriceInfo {
        return CoinPriceInfo(
            dbModelCoinPriceInfo.fromSymbol,
            dbModelCoinPriceInfo.toSymbol,
            dbModelCoinPriceInfo.price,
            dbModelCoinPriceInfo.lastUpdate,
            dbModelCoinPriceInfo.highDay,
            dbModelCoinPriceInfo.lowDay,
            dbModelCoinPriceInfo.lastMarket,
            dbModelCoinPriceInfo.imageUrl
        )
    }

    fun mapListEntityToListDBModelCoinPriceInfo(coinPriceInfoList: List<CoinPriceInfo>)
            : List<DbModelCoinPriceInfo> {
        return coinPriceInfoList.map { mapEntityToDbModel(it) }
    }



}