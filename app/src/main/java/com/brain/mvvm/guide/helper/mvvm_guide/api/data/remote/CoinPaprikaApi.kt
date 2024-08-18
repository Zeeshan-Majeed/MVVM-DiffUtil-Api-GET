package com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote

import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.CoinDetailDto
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}