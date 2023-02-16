package com.example.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.data.database.MapperDB
import com.example.data.database.room.DatabaseCoins
import com.example.data.network.DtoCoinPriceInfo
import com.example.data.network.MapperDTO
import com.example.data.network.retrofit.ApiFactory
import com.example.data.utils.getFormattedLastUpdateTime
import com.example.data.utils.getFullImage
import com.example.domain.Repository
import com.example.domain.entity.CoinPriceInfo
import com.example.domain.entity.DetailOfCoinsResponse
import com.google.gson.Gson

class RepositoryImpl(private val application: Application):Repository {

    private val mapperDB = MapperDB()
    private val mapperDTO = MapperDTO()

    private val api = ApiFactory.apiService
    private val dao = DatabaseCoins.getInstance(application).getDao()


    override suspend fun getTopCoins(): LiveData<List<CoinPriceInfo>> {
            updateCoinsDb()

            val temp = dao.getPriceList()
            val mediatorLiveData = MediatorLiveData<List<CoinPriceInfo>>()
                .apply {
                    addSource(temp) {
                        it.map {
                            it.imageUrl = getFullImage(it.imageUrl)
                            it.lastUpdate = getFormattedLastUpdateTime(it.lastUpdate)
                        }
                        value = it.map { mapperDB.mapDbModelToEntity(it) }
                    }
                }
            return mediatorLiveData
        }

    override suspend fun getDetailInfoAboutSingleCoin(coinSym:String): LiveData<CoinPriceInfo> {
        val coinPriceInfoDbModelLD = dao.getPriceInfoAboutCoin(coinSym)
        val mediatorLiveData = MediatorLiveData<CoinPriceInfo>()
            .apply {
                   addSource(coinPriceInfoDbModelLD){
                       it.imageUrl = getFullImage(it.imageUrl)
                       it.lastUpdate = getFormattedLastUpdateTime(it.lastUpdate)
                       value = mapperDB.mapDbModelToEntity(it)
                   }
        }
        return mediatorLiveData
    }

    private suspend fun updateCoinsDb(){
        val dtoListCoins = api.getTopCoins().listOfCoins
        dtoListCoins?.let {
            val namesOfCoins = it.map { it?.coinInfo?.name }.joinToString(",")
            Log.d("ARSEN", "getTopCoins: namesOfCoins: $namesOfCoins ")

            val listDetailInfo = getDetailInfoAboutCoins(namesOfCoins)
            dao.insertDataOnDatabase(mapperDB.mapListEntityToListDBModelCoinPriceInfo(listDetailInfo))

        }
    }
    private suspend fun getDetailInfoAboutCoins(namesCoins: String): List<CoinPriceInfo> {
        val dtoDetailOfCoinsResponse = api.getFullPriceList(fromSym = namesCoins)
        val detailOfCoinsResponse =
            mapperDTO.mapDtoDetailOfCoinResponseToEntity(dtoDetailOfCoinsResponse)

        val dtoListCoinPriceInfo =
            getPriceInfoListFromDetailOfCoinsResponse(detailOfCoinsResponse)

        return dtoListCoinPriceInfo.map { mapperDTO.dtoToEntity(it) }
    }


    private fun getPriceInfoListFromDetailOfCoinsResponse(detailOfCoinsResponse: DetailOfCoinsResponse): List<DtoCoinPriceInfo> {
        val result: MutableList<DtoCoinPriceInfo> = mutableListOf()
        val jsonObject = detailOfCoinsResponse.coinPriceInfoJsonObject ?: return result

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

}
