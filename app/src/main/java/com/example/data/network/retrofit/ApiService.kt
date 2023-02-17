package com.example.data.network.retrofit


import com.example.data.network.DtoCoinsNameList
import com.example.data.network.DtoCoinInfoJsonContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull?")
    suspend fun getTopCoins(@Query(QUERY_PARAM_API_KEY) apikey: String = MY_KEY,
                    @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
                    @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = "USD"
    ): DtoCoinsNameList


    @GET("pricemultifull?")
    suspend fun getFullPriceList(@Query(QUERY_PARAM_API_KEY) apikey: String = MY_KEY,
                         @Query(QUERY_PARAM_FROM_SYMBOLS) fromSym: String,
                         @Query(QUERY_PARAM_TO_SYMBOLS) toSym: String = "USD")
    : DtoCoinInfoJsonContainer


    companion object {
        const val MY_KEY = "a40bfe6e0c6c0230dc3dca96f71652536fd0241b627c954a0c6f2eed979614c6"

        const val QUERY_PARAM_API_KEY = "api_key"
        const val QUERY_PARAM_LIMIT = "limit"
        const val QUERY_PARAM_TO_SYMBOL = "tsym"

        const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
    }
}
