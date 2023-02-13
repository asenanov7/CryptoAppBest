package com.example.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(                //CoinInfo
    @Expose
    @SerializedName("Name")
    val name:String? = null
)