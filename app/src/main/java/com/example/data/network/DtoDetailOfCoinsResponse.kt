package com.example.data.network

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class DtoDetailOfCoinsResponse(
    @SerializedName("RAW")
    val coinPriceInfoJsonObject: JsonObject? //Будет содержать что конвертировать(крипта) и во что (доллары)
)
