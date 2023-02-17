package com.example.data

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.data.database.MapperDB
import com.example.data.database.room.DatabaseCoins
import com.example.data.network.MapperDTO
import com.example.data.network.retrofit.ApiFactory
import com.example.data.utils.getFormattedLastUpdateTime
import com.example.data.utils.getFullImage
import com.example.data.workers.LoadDataWorker
import com.example.domain.Repository
import com.example.domain.CoinPriceInfo
import kotlinx.coroutines.delay

class RepositoryImpl(private val application: Application) : Repository {

    private val mapperDB = MapperDB()

    private val dao = DatabaseCoins.getInstance(application).getDao()

    override suspend fun getTopCoins(): LiveData<List<CoinPriceInfo>> {

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

    override suspend fun getDetailInfoAboutSingleCoin(coinSym: String): LiveData<CoinPriceInfo> {
        val coinPriceInfoDbModelLD = dao.getPriceInfoAboutCoin(coinSym)
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
            LoadDataWorker.makeRequest()
        )
    }


}
