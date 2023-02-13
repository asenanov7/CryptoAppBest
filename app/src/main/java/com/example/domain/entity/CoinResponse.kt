package com.example.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinsResponse(        //CoinInfoListOfData
    @Expose
    @SerializedName("Data")
    val listOfCoins:List<Coin?>? = null
)
