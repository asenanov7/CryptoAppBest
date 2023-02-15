package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class DbModelCoinPriceInfo (

    @PrimaryKey(autoGenerate = false)
        val fromSymbol : String,

    val toSymbol : String,
    val market : String,
    val price : String,
    var lastUpdate : String,
    val lastVolume : String,
    val lastVolumeTo : String,
    val lastTradeID : String,
    val volumeDay : String,
    val volumeDayTo : String,
    val volume24Hour : String,
    val volume24HourTo : String,
    val openDay : String,
    val highDay : String,
    val lowDay : String,
    val open24Hour : String,
    val high24Hour : String,
    val low24Hour : String,
    val lastMarket : String,
    val volumeHour : String,
    val volumeHourTo : String,
    val openHour : String,
    val highHour : String,
    val lowHour : String,
    val topTierVolume24Hour : String,
    val topTierVolume24HourTo : String,
    val change24Hour : String,
    val changePCT24Hour : Double,
    val changeDay : String,
    val changePCTDay : String,
    val changeHour : String,
    val changePCTHour : String,
    val conversionType : String,
    val conversionSymbol : String,
    val supply : String,
    val mktCap : String,
    val mktCapPenalty : String,
    val circulatingSupply : String,
    val circulatingSupplyMktCap : String,
    val totalVolume24h : String,
    val totalVolume24hTo : String,
    val totalTopTierVolume24h : String,
    val totalTopTierVolume24hTo : String,
    var imageUrl : String
    ):java.io.Serializable
