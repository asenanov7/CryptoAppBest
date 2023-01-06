package com.example.cryptoapp2.retrofit


import com.example.cryptoapp2.pojo.CoinsResponse
import com.example.cryptoapp2.pojo.DetailOfCoinsResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull?")
    fun getTopCoins(@Query(QUERY_PARAM_API_KEY) apikey: String = MY_KEY,
                    @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
                    @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = "USD"
    ): Single<CoinsResponse>


    @GET("pricemultifull?")
    fun getFullPriceList(@Query(QUERY_PARAM_API_KEY) apikey: String = MY_KEY,
                         @Query(QUERY_PARAM_FROM_SYMBOLS) fromSym: String,
                         @Query(QUERY_PARAM_TO_SYMBOLS) toSym: String = "USD")
    : Single<DetailOfCoinsResponse>


    companion object {
        const val MY_KEY = "a40bfe6e0c6c0230dc3dca96f71652536fd0241b627c954a0c6f2eed979614c6"

        const val QUERY_PARAM_API_KEY = "api_key"
        const val QUERY_PARAM_LIMIT = "limit"
        const val QUERY_PARAM_TO_SYMBOL = "tsym"

        const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
    }
}

object ApiFactory {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val apiService = retrofit.create<ApiService>()

}

