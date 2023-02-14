package com.example.data.network

import com.example.domain.entity.CoinPriceInfo
import com.example.domain.entity.DetailOfCoinsResponse

class MapperDTO {

    fun entityToDto(coinPriceInfo:CoinPriceInfo): DtoCoinPriceInfo {
         return DtoCoinPriceInfo(
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

    fun dtoToEntity(dtoCoinPriceInfo: DtoCoinPriceInfo):CoinPriceInfo{
        return CoinPriceInfo(
                dtoCoinPriceInfo.fromSymbol,
                dtoCoinPriceInfo.toSymbol,
                dtoCoinPriceInfo.market,
                dtoCoinPriceInfo.price,
                dtoCoinPriceInfo.lastUpdate,
                dtoCoinPriceInfo.lastVolume,
                dtoCoinPriceInfo.lastVolumeTo,
                dtoCoinPriceInfo.lastTradeID,
                dtoCoinPriceInfo.volumeDay,
                dtoCoinPriceInfo.volumeDayTo,
                dtoCoinPriceInfo.volume24Hour,
                dtoCoinPriceInfo.volume24HourTo,
                dtoCoinPriceInfo.openDay,
                dtoCoinPriceInfo.highDay,
                dtoCoinPriceInfo.lowDay,
                dtoCoinPriceInfo.open24Hour,
                dtoCoinPriceInfo.high24Hour,
                dtoCoinPriceInfo.low24Hour,
                dtoCoinPriceInfo.lastMarket,
                dtoCoinPriceInfo.volumeHour,
                dtoCoinPriceInfo.volumeHourTo,
                dtoCoinPriceInfo.openHour,
                dtoCoinPriceInfo.highHour,
                dtoCoinPriceInfo.lowHour,
                dtoCoinPriceInfo.topTierVolume24Hour,
                dtoCoinPriceInfo.topTierVolume24HourTo,
                dtoCoinPriceInfo.change24Hour,
                dtoCoinPriceInfo.changePCT24Hour,
                dtoCoinPriceInfo.changeDay,
                dtoCoinPriceInfo.changePCTDay,
                dtoCoinPriceInfo.changeHour,
                dtoCoinPriceInfo.changePCTHour,
                dtoCoinPriceInfo.conversionType,
                dtoCoinPriceInfo.conversionSymbol,
                dtoCoinPriceInfo.supply,
                dtoCoinPriceInfo.mktCap,
                dtoCoinPriceInfo.mktCapPenalty,
                dtoCoinPriceInfo.circulatingSupply,
                dtoCoinPriceInfo.circulatingSupplyMktCap,
                dtoCoinPriceInfo.totalVolume24h,
                dtoCoinPriceInfo.totalVolume24hTo,
                dtoCoinPriceInfo.totalTopTierVolume24h,
                dtoCoinPriceInfo.totalTopTierVolume24hTo,
                dtoCoinPriceInfo.imageUrl
        )
    }


    fun mapDtoDetailOfCoinResponseToEntity(dtoDetailOfCoinsResponse: DtoDetailOfCoinsResponse): DetailOfCoinsResponse {
        return DetailOfCoinsResponse(
            dtoDetailOfCoinsResponse.coinPriceInfoJsonObject
        )
    }





}