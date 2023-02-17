package com.example.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DtoCoinNameContainer(
    @Expose
    @SerializedName("CoinInfo")
    val coinInfo: DtoCoinName? = null,
)

