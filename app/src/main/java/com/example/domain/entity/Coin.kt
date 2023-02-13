package com.example.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coin(                //Datum
        @Expose
        @SerializedName("CoinInfo")
        val coinInfo: CoinInfo? = null
    )

