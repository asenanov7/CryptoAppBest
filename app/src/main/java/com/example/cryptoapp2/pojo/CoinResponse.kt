package com.example.cryptoapp2.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinsResponse(        //CoinInfoListOfData
    @Expose
    @SerializedName("Data")
    val listOfCoins:List<Coin?>? = null
)

data class Coin(                  //Datum
    @Expose
    @SerializedName("CoinInfo")
    val coinInfo:CoinInfo? = null
)

data class CoinInfo(                //CoinInfo
    @Expose
    @SerializedName("Name")
    val name:String? = null
)

