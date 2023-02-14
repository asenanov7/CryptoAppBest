package com.example.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DtoCoinsResponse(
    @Expose
    @SerializedName("Data")
    val listOfCoins: List<DtoCoin?>? = null,
)

