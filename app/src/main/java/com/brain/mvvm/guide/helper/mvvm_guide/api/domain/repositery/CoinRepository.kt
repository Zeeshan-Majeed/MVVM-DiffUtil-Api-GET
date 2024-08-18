package com.brain.mvvm.guide.helper.mvvm_guide.api.domain.repositery

import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.CoinDetailDto
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}