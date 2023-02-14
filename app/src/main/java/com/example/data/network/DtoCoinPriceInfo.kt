package com.example.data.network

import com.google.gson.annotations.SerializedName

data class DtoCoinPriceInfo (

        @SerializedName("FROMSYMBOL")
        val fromSymbol : String,

        @SerializedName("TOSYMBOL")
        val toSymbol : String,

        @SerializedName("MARKET")
        val market : String,

        @SerializedName("PRICE")
        val price : String,

        @SerializedName("LASTUPDATE")
        val lastUpdate : String,

        @SerializedName("LASTVOLUME")
        val lastVolume : String,

        @SerializedName("LASTVOLUMETO")
        val lastVolumeTo : String,

        @SerializedName("LASTTRADEID")
        val lastTradeID : String,

        @SerializedName("VOLUMEDAY")
        val volumeDay : String,

        @SerializedName("VOLUMEDAYTO")
        val volumeDayTo : String,

        @SerializedName("VOLUME24HOUR")
        val volume24Hour : String,

        @SerializedName("VOLUME24HOURTO")
        val volume24HourTo : String,

        @SerializedName("OPENDAY")
        val openDay : String,

        @SerializedName("HIGHDAY")
        val highDay : String,

        @SerializedName("LOWDAY")
        val lowDay : String,

        @SerializedName("OPEN24HOUR")
        val open24Hour : String,

        @SerializedName("HIGH24HOUR")
        val high24Hour : String,

        @SerializedName("LOW24HOUR")
        val low24Hour : String,

        @SerializedName("LASTMARKET")
        val lastMarket : String,

        @SerializedName("VOLUMEHOUR")
        val volumeHour : String,

        @SerializedName("VOLUMEHOURTO")
        val volumeHourTo : String,

        @SerializedName("OPENHOUR")
        val openHour : String,

        @SerializedName("HIGHHOUR")
        val highHour : String,

        @SerializedName("LOWHOUR")
        val lowHour : String,

        @SerializedName("TOPTIERVOLUME24HOUR")
        val topTierVolume24Hour : String,

        @SerializedName("TOPTIERVOLUME24HOURTO")
        val topTierVolume24HourTo : String,

        @SerializedName("CHANGE24HOUR")
        val change24Hour : String,

        @SerializedName("CHANGEPCT24HOUR")
        val changePCT24Hour : Double,

        @SerializedName("CHANGEDAY")
        val changeDay : String,

        @SerializedName("CHANGEPCTDAY")
        val changePCTDay : String,

        @SerializedName("CHANGEHOUR")
        val changeHour : String,

        @SerializedName("CHANGEPCTHOUR")
        val changePCTHour : String,

        @SerializedName("CONVERSIONTYPE")
        val conversionType : String,

        @SerializedName("CONVERSIONSYMBOL")
        val conversionSymbol : String,

        @SerializedName("SUPPLY")
        val supply : String,

        @SerializedName("MKTCAP")
        val mktCap : String,

        @SerializedName("MKTCAPPENALTY")
        val mktCapPenalty : String,

        @SerializedName("CIRCULATINGSUPPLY")
        val circulatingSupply : String,

        @SerializedName("CIRCULATINGSUPPLYMKTCAP")
        val circulatingSupplyMktCap : String,

        @SerializedName("TOTALVOLUME24H")
        val totalVolume24h : String,

        @SerializedName("TOTALVOLUME24HTO")
        val totalVolume24hTo : String,

        @SerializedName("TOTALTOPTIERVOLUME24H")
        val totalTopTierVolume24h : String,

        @SerializedName("TOTALTOPTIERVOLUME24HTO")
        val totalTopTierVolume24hTo : String,

        @SerializedName("IMAGEURL")
        val imageUrl : String
    ):java.io.Serializable


