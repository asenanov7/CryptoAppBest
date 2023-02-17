package com.example.domain


data class CoinPriceInfo(         //CoinPriceInfo

    val fromSymbol : String,
    val toSymbol : String,
    val price : String,
    val lastUpdate : String,
    val highDay : String,
    val lowDay : String,
    val lastMarket : String,
    val imageUrl : String

):java.io.Serializable
