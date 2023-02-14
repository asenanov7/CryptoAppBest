package com.example.data

import android.app.Application
import android.util.Log
import android.view.SurfaceControl.Transaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.example.data.database.MapperDB
import com.example.data.database.room.DatabaseCoins
import com.example.data.network.DtoCoinPriceInfo
import com.example.data.network.MapperDTO
import com.example.data.network.retrofit.ApiFactory
import com.example.domain.Repository
import com.example.domain.entity.CoinPriceInfo
import com.example.domain.entity.DetailOfCoinsResponse
import com.google.gson.Gson

class RepositoryImpl(application: Application):Repository {

    private val mapperDB = MapperDB()
    private val mapperDTO = MapperDTO()
    private val api = ApiFactory.apiService
    private val dao = DatabaseCoins.getInstance(application).getDao()


    override suspend fun getTopCoins(): LiveData<List<CoinPriceInfo>> {
        val dtoListCoins = api.getTopCoins().listOfCoins
        dtoListCoins?.let {
            val namesOfCoins = it.map { it?.coinInfo?.name }.joinToString(",")
            Log.d("ARSEN", "getTopCoins: namesOfCoins: $namesOfCoins ")

            val dtoDetailOfCoinsResponse = api.getFullPriceList(fromSym = namesOfCoins)
            val  detailOfCoinsResponse = mapperDTO.mapDtoDetailOfCoinResponseToEntity(dtoDetailOfCoinsResponse)

            val dtoListCoinPriceInfo =
                getPriceInfoListFromDetailOfCoinsResponse(detailOfCoinsResponse)

            val listCoinPriceInfo = dtoListCoinPriceInfo.map { mapperDTO.dtoToEntity(it) }
            val dbModelCoinPriceInfo =
                mapperDB.mapListEntityToListDBModelCoinPriceInfo(listCoinPriceInfo)
            dao.insertDataOnDatabase(dbModelCoinPriceInfo)

        }
        val temp = dao.getPriceList()
        val mediatorLiveData  = MediatorLiveData<List<CoinPriceInfo>>()
            .apply {
            addSource(temp){
                value = it.map { mapperDB.mapDbModelToEntity(it) }
            }
        }
        return mediatorLiveData
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
