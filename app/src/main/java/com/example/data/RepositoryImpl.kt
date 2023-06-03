package com.example.data

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.data.database.MapperDB
import com.example.data.database.room.DatabaseCoinsDao
import com.example.data.utils.getFormattedLastUpdateTime
import com.example.data.utils.getFullImage
import com.example.data.workers.LoadDataWorker
import com.example.domain.CoinPriceInfo
import com.example.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mapperDB: MapperDB,
    private val databaseDao: DatabaseCoinsDao,
    private val application: Application,
) : Repository {

    override suspend fun getTopCoins(): Flow<List<CoinPriceInfo>> =
            databaseDao.getPriceList().map { it.map { mapperDB.mapDbModelToEntity(it) } }


    override suspend fun getDetailInfoAboutSingleCoin(coinSym: String): Flow<CoinPriceInfo> =
        databaseDao.getPriceInfoAboutCoin(coinSym).map { mapperDB.mapDbModelToEntity(it) }


    override fun loadData() {
        WorkManager.getInstance(application).apply {
            enqueueUniqueWork(
                LoadDataWorker.NAME,
                ExistingWorkPolicy.REPLACE,
                LoadDataWorker.makeRequest()  //создание воркера
            )
        }
    }


}
