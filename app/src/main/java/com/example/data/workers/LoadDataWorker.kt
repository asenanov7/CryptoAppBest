package com.example.data.workers

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.data.database.MapperDB
import com.example.data.database.room.DatabaseCoinsDao
import com.example.data.network.MapperDTO
import com.example.data.network.retrofit.ApiService
import com.example.data.utils.getFormattedLastUpdateTime
import com.example.data.utils.getFullImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadDataWorker(

    context: Context,
    workerParameters: WorkerParameters,
    private val api: ApiService,
    private val dao: DatabaseCoinsDao,
    private val mapperDB: MapperDB,
    private val mapperDTO: MapperDTO,

    ) : Worker(context, workerParameters) {

    class Factory @Inject constructor(
        private val api: ApiService,
        private val dao: DatabaseCoinsDao,
        private val mapperDB: MapperDB,
        private val mapperDTO: MapperDTO,
    ) : ChildWorkerFactory {
        override fun create(context: Context, workerParameters: WorkerParameters, ): ListenableWorker {
            return LoadDataWorker(
                context, workerParameters, api, dao, mapperDB, mapperDTO
            )
        }
    }

    override fun doWork(): Result {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
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

                    listModelCoinPriceInfo.map {
                        it.imageUrl = getFullImage(it.imageUrl)
                        it.lastUpdate = getFormattedLastUpdateTime(it.lastUpdate)
                    }

                    dao.insertDataOnDatabase(listModelCoinPriceInfo)
                } catch (_: Exception) {
                }

                delay(10000)
            }
        }
        Log.d("ARSEN", "мы сюда дошли")
        return Result.retry()
    }

    companion object {
        const val NAME = "LoadDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(LoadDataWorker::class.java).build()
        }
    }

}