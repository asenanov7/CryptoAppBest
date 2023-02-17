package com.example.data.network

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class DtoCoinInfoJsonContainer(
    @SerializedName("RAW")
    val json: JsonObject? //Будет содержать что конвертировать(крипта) и во что (доллары)
)
