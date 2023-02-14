package com.example.data.database

import com.example.data.network.DtoCoinPriceInfo
import com.example.domain.entity.CoinPriceInfo

class MapperDB {

    fun mapEntityToDbModel(coinPriceInfo: CoinPriceInfo): DbModelCoinPriceInfo{
        return DbModelCoinPriceInfo(
            coinPriceInfo.fromSymbol,
            coinPriceInfo.toSymbol,
            coinPriceInfo.market,
            coinPriceInfo.price,
            coinPriceInfo.lastUpdate,
            coinPriceInfo.lastVolume,
            coinPriceInfo.lastVolumeTo,
            coinPriceInfo.lastTradeID,
            coinPriceInfo.volumeDay,
            coinPriceInfo.volumeDayTo,
            coinPriceInfo.volume24Hour,
            coinPriceInfo.volume24HourTo,
            coinPriceInfo.openDay,
            coinPriceInfo.highDay,
            coinPriceInfo.lowDay,
            coinPriceInfo.open24Hour,
            coinPriceInfo.high24Hour,
            coinPriceInfo.low24Hour,
            coinPriceInfo.lastMarket,
            coinPriceInfo.volumeHour,
            coinPriceInfo.volumeHourTo,
            coinPriceInfo.openHour,
            coinPriceInfo.highHour,
            coinPriceInfo.lowHour,
            coinPriceInfo.topTierVolume24Hour,
            coinPriceInfo.topTierVolume24HourTo,
            coinPriceInfo.change24Hour,
            coinPriceInfo.changePCT24Hour,
            coinPriceInfo.changeDay,
            coinPriceInfo.changePCTDay,
            coinPriceInfo.changeHour,
            coinPriceInfo.changePCTHour,
            coinPriceInfo.conversionType,
            coinPriceInfo.conversionSymbol,
            coinPriceInfo.supply,
            coinPriceInfo.mktCap,
            coinPriceInfo.mktCapPenalty,
            coinPriceInfo.circulatingSupply,
            coinPriceInfo.circulatingSupplyMktCap,
            coinPriceInfo.totalVolume24h,
            coinPriceInfo.totalVolume24hTo,
            coinPriceInfo.totalTopTierVolume24h,
            coinPriceInfo.totalTopTierVolume24hTo,
            coinPriceInfo.imageUrl
        )
    }

    fun mapDbModelToEntity(dbModelCoinPriceInfo: DbModelCoinPriceInfo): CoinPriceInfo {
        return CoinPriceInfo(
            dbModelCoinPriceInfo.fromSymbol,
            dbModelCoinPriceInfo.toSymbol,
            dbModelCoinPriceInfo.market,
            dbModelCoinPriceInfo.price,
            dbModelCoinPriceInfo.lastUpdate,
            dbModelCoinPriceInfo.lastVolume,
            dbModelCoinPriceInfo.lastVolumeTo,
            dbModelCoinPriceInfo.lastTradeID,
            dbModelCoinPriceInfo.volumeDay,
            dbModelCoinPriceInfo.volumeDayTo,
            dbModelCoinPriceInfo.volume24Hour,
            dbModelCoinPriceInfo.volume24HourTo,
            dbModelCoinPriceInfo.openDay,
            dbModelCoinPriceInfo.highDay,
            dbModelCoinPriceInfo.lowDay,
            dbModelCoinPriceInfo.open24Hour,
            dbModelCoinPriceInfo.high24Hour,
            dbModelCoinPriceInfo.low24Hour,
            dbModelCoinPriceInfo.lastMarket,
            dbModelCoinPriceInfo.volumeHour,
            dbModelCoinPriceInfo.volumeHourTo,
            dbModelCoinPriceInfo.openHour,
            dbModelCoinPriceInfo.highHour,
            dbModelCoinPriceInfo.lowHour,
            dbModelCoinPriceInfo.topTierVolume24Hour,
            dbModelCoinPriceInfo.topTierVolume24HourTo,
            dbModelCoinPriceInfo.change24Hour,
            dbModelCoinPriceInfo.changePCT24Hour,
            dbModelCoinPriceInfo.changeDay,
            dbModelCoinPriceInfo.changePCTDay,
            dbModelCoinPriceInfo.changeHour,
            dbModelCoinPriceInfo.changePCTHour,
            dbModelCoinPriceInfo.conversionType,
            dbModelCoinPriceInfo.conversionSymbol,
            dbModelCoinPriceInfo.supply,
            dbModelCoinPriceInfo.mktCap,
            dbModelCoinPriceInfo.mktCapPenalty,
            dbModelCoinPriceInfo.circulatingSupply,
            dbModelCoinPriceInfo.circulatingSupplyMktCap,
            dbModelCoinPriceInfo.totalVolume24h,
            dbModelCoinPriceInfo.totalVolume24hTo,
            dbModelCoinPriceInfo.totalTopTierVolume24h,
            dbModelCoinPriceInfo.totalTopTierVolume24hTo,
            dbModelCoinPriceInfo.imageUrl
        )
    }

    fun mapListEntityToListDBModelCoinPriceInfo(coinPriceInfoList: List<CoinPriceInfo>)
            :List<DbModelCoinPriceInfo>{
        return coinPriceInfoList.map { mapEntityToDbModel(it) }
    }




}