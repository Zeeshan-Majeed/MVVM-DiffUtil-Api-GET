package com.brain.mvvm.guide.helper.mvvm_guide.api.domain.models

import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)