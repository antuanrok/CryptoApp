package com.example.cryptoapp.api

import com.example.cryptoapp.pojo.CoinInfoListOfData
import com.example.cryptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


   @GET("top/totalvolfull")
    fun  getTopCoinsInfo(
       @Query(QUERY_PARAM_APY_KEY) apiKey: String = "",
       @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
       @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY,
    ): Single<CoinInfoListOfData>


    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_APY_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fsyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY,
    ):Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_APY_KEY = "api_key"
        private const val CURRENCY = "USD"

        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

    }
}