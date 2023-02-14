package com.example.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DtoCoin(
    @Expose
    @SerializedName("CoinInfo")
    val coinInfo: DtoCoinInfo? = null,
)

