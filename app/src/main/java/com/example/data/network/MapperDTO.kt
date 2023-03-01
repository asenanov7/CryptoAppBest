package com.example.data.network

import com.example.domain.CoinPriceInfo
import com.google.gson.Gson
import javax.inject.Inject

class MapperDTO @Inject constructor() {

    fun mapDtoToEntity(dtoCoinPriceInfo: DtoCoinPriceInfo): CoinPriceInfo {
        return CoinPriceInfo(
            dtoCoinPriceInfo.fromSymbol,
            dtoCoinPriceInfo.toSymbol,
            dtoCoinPriceInfo.price,
            dtoCoinPriceInfo.lastUpdate,
            dtoCoinPriceInfo.highDay,
            dtoCoinPriceInfo.lowDay,
            dtoCoinPriceInfo.lastMarket,
            dtoCoinPriceInfo.imageUrl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: DtoCoinInfoJsonContainer): List<DtoCoinPriceInfo>{
        val result: MutableList<DtoCoinPriceInfo> = mutableListOf()
        val jsonObject = jsonContainer.json ?: return result

        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinPriceInfo = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey),
                    DtoCoinPriceInfo::class.java
                ) //создаем объект нашего класса
                result.add(coinPriceInfo)
            }
        }
        return result
    }

    fun mapDtoCoinNameListToString(coinNameContainers: List<DtoCoinNameContainer?>?): String {
        return coinNameContainers?.map { it?.coinInfo?.name }?.joinToString ("," )?:""
    }


}