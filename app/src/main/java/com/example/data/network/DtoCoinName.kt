package com.example.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DtoCoinName (
@Expose
@SerializedName("Name")
val name:String? = null
)