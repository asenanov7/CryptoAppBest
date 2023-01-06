package com.example.cryptoapp2.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp2.pojo.CoinPriceInfo

@Dao
interface DatabaseCoinsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataOnDatabase(priceList: List<CoinPriceInfo>)               //Метод для сохранения полученных из интернета данных о валютах в базу

    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC ")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>                       //Вся информация о всех сохраненных данных о валютах в локальной базе

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym")
    fun getPriceInfoAboutCoin(fSym:String): LiveData<CoinPriceInfo>        //Вся детальная информация по одной валюте

}

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class DatabaseCoins : RoomDatabase() {
    companion object {
        private var db: DatabaseCoins? = null

        fun getInstance(context: Context): DatabaseCoins {
                db?.let { return it }     //Этот код выполнится только если бд не равна null
                db = Room.databaseBuilder(context, DatabaseCoins::class.java, "db").build()
                return db!!
            }

    }
    abstract fun getDao(): DatabaseCoinsDao
}