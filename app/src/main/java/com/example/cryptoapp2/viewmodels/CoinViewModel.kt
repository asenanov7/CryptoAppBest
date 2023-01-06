package com.example.cryptoapp2.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoapp2.pojo.CoinPriceInfo
import com.example.cryptoapp2.pojo.DetailOfCoinsResponse
import com.example.cryptoapp2.retrofit.ApiFactory
import com.example.cryptoapp2.room.DatabaseCoins
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application):AndroidViewModel(application) {

    private val dbDao = DatabaseCoins.getInstance(application).getDao()

    val priceList = dbDao.getPriceList()
    fun getDetailInfo(fSym:String): LiveData<CoinPriceInfo> = dbDao.getPriceInfoAboutCoin(fSym)

    private val compositeDisposable = CompositeDisposable()

    private fun loadData(){
        val disposable = ApiFactory.apiService.getTopCoins()
            .map { it.listOfCoins?.map { it?.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fromSym = it) }
            .map { getPriceInfoListFromDetailOfCoinsResponse(it) }
            .delaySubscription(10, TimeUnit.SECONDS)//Задержка на логику на всю цепочку. А делай только на подписку
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { dbDao.insertDataOnDatabase(it) ; Log.d("TAG", "Insert data on db")},
                { Log.d("TAG", "loadData: Bad:${it.message}")  }
            )

        compositeDisposable.add(disposable)
    }

    private fun getPriceInfoListFromDetailOfCoinsResponse(detailOfCoinsResponse: DetailOfCoinsResponse): List<CoinPriceInfo> {
            val result:MutableList<CoinPriceInfo> = mutableListOf()

            val jsonObject = detailOfCoinsResponse.coinPriceInfoJsonObject?:return result


            val coinKeySet = jsonObject.keySet()                                  //Получаем ключи криптовалют /BTC,ETH,BUSD,USDT,XRP,SOL,DOGE,LTC,ETC,BNB/
            for (coinKey in coinKeySet){                                                      //для каждого ключа криптовалюты
                val currencyJson = jsonObject.getAsJsonObject(coinKey)                    //получаем Json объект по ключу криптовалюты
                val currencyKeySet = currencyJson.keySet()                        //У полученного нами объекта, получаем вложенные ключи(валюты) /USD,EUR/
                for (currencyKey in currencyKeySet ){                                         //и уже по ключу каждой валюты
                    val coinPriceInfo = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey), CoinPriceInfo::class.java) //создаем объект нашего класса
                    result.add(coinPriceInfo)
                }
            }
        return result
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    init {
        loadData()
    }

}
