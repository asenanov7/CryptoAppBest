package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class DbModelCoinPriceInfo(

    @PrimaryKey(autoGenerate = false)
    val fromSymbol: String,

    val toSymbol: String,
    val price: String,
    var lastUpdate: String,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    var imageUrl: String,
) : java.io.Serializable
