package com.example.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkerParameters
import com.example.data.database.MapperDB
import com.example.data.database.room.DatabaseCoins
import com.example.data.network.MapperDTO
import com.example.data.network.retrofit.ApiFactory
import kotlinx.coroutines.delay

class LoadDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    private val api = ApiFactory.apiService
    private val dao = DatabaseCoins.getInstance(context).getDao()

    private val mapperDB = MapperDB()
    private val mapperDTO = MapperDTO()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                Log.d("ARSEN", "do work: loadData: ")
                val dtoListCoins = api.getTopCoins().listOfCoins
                val namesOfCoins = mapperDTO.mapDtoCoinNameListToString(dtoListCoins)

                val dtoJsonContainer =
                    api.getFullPriceList(fromSym = namesOfCoins)

                val listDtoCoinPriceInfo =
                    mapperDTO.mapJsonContainerToListCoinInfo(dtoJsonContainer)

                val listCoinPriceInfo =
                    listDtoCoinPriceInfo.map { mapperDTO.mapDtoToEntity(it) }

                val listModelCoinPriceInfo =
                    mapperDB.mapListEntityToListDBModelCoinPriceInfo(listCoinPriceInfo)

                dao.insertDataOnDatabase(listModelCoinPriceInfo)
            } catch (_: Exception) {
            }

            delay(10000)
        }

    }

    companion object {
        const val NAME = "LoadDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(LoadDataWorker::class.java).build()
        }
    }

}