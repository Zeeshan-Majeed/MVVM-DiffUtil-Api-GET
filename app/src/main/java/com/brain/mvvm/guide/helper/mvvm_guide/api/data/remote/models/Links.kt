package com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models

import com.google.gson.annotations.SerializedName

data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
    val website: List<String>,
    val youtube: List<String>

)
