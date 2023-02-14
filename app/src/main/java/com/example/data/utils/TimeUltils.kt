package com.example.data.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimeStamp(sec:Long):String{
    val timeStamp = Timestamp(sec*1000)
    val date = Date(timeStamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()

    return sdf.format(date)


}
fun getFormattedLastUpdateTime(lastUpdate:String): String {
    return convertTimeStamp(lastUpdate.toLong())
}

fun getFullImage(imageUrl:String):String{
    return "https://cryptocompare.com$imageUrl"
}