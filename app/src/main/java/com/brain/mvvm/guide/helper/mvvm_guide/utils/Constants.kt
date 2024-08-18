package com.brain.mvvm.guide.helper.mvvm_guide.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object Constants {
    const val BASE_URL = "https://api.coinpaprika.com/"

    var COIN_ID = "coin_id"

    /**
     * If you want to use in Activity replace
     * @LifecycleOwner with
     * @AppCompatActivity
     */
    fun <T> LifecycleOwner.collectLatestLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    /**
     * If you want to use in Activity replace
     * @LifecycleOwner with
     * @AppCompatActivity
     */
    fun <T> /*AppCompatActivity*/LifecycleOwner.collectLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }
    }
}