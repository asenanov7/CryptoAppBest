package com.example.data.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.DbModelCoinPriceInfo

@Dao
interface DatabaseCoinsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataOnDatabase(priceList: List<DbModelCoinPriceInfo>)               //Метод для сохранения полученных из интернета данных о валютах в базу

    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC ")
    fun getPriceList(): LiveData<List<DbModelCoinPriceInfo>>                       //Вся информация о всех сохраненных данных о валютах в локальной базе

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym")
    fun getPriceInfoAboutCoin(fSym:String): LiveData<DbModelCoinPriceInfo>        //Вся детальная информация по одной валюте

}