package com.example.data

import android.app.Application
import androidx.lifecycle.LiveData
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
import javax.inject.Inject

class RepositoryImpl @Inject constructor (
    private val mapperDB: MapperDB,
    private val databaseDao: DatabaseCoinsDao,
    private val application: Application
        ) : Repository {

    override suspend fun getTopCoins(): LiveData<List<CoinPriceInfo>> {

        val temp = databaseDao.getPriceList()
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

    override suspend fun getDetailInfoAboutSingleCoin(coinSym: String): LiveData<CoinPriceInfo> {
        val coinPriceInfoDbModelLD = databaseDao.getPriceInfoAboutCoin(coinSym)
        val mediatorLiveData = MediatorLiveData<CoinPriceInfo>()
            .apply {
                addSource(coinPriceInfoDbModelLD) {
                    it.imageUrl = getFullImage(it.imageUrl)
                    it.lastUpdate = getFormattedLastUpdateTime(it.lastUpdate)
                    value = mapperDB.mapDbModelToEntity(it)
                }
            }
        return mediatorLiveData
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            LoadDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            LoadDataWorker.makeRequest()  //создание воркера
        )
    }


}
