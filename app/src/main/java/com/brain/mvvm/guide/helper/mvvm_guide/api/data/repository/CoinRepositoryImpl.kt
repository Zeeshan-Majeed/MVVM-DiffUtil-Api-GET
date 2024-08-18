package com.brain.mvvm.guide.helper.mvvm_guide.api.data.repository

import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.CoinPaprikaApi
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.CoinDetailDto
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.CoinDto
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.repositery.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}