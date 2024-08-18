package com.brain.mvvm.guide.helper.mvvm_guide.api.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.toCoin
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.models.Coin
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.repositery.CoinRepository
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _getAllCoins = MutableStateFlow<Resource<List<Coin>>>(Resource.Loading())
    val getAllCoins = _getAllCoins.asStateFlow()

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        _getAllCoins.value = Resource.Loading()
        viewModelScope.launch {
            try {
                /**
                 * Fetch data and map CoinDto to Coin
                 */
                val coins = repository.getCoins().map { it.toCoin() }
                if (coins != null)
                    _getAllCoins.value = Resource.Success(coins)
                else
                    _getAllCoins.value = Resource.Error("Nothing found")
            } catch (e: Exception) {
                _getAllCoins.value = Resource.Error("Failed to fetch coins: ${e.message}")
            }
        }
    }
}