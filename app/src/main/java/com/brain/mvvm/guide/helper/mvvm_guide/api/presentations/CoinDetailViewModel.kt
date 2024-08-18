package com.brain.mvvm.guide.helper.mvvm_guide.api.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brain.mvvm.guide.helper.mvvm_guide.api.data.remote.models.toCoinDetail
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.models.CoinDetail
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.repositery.CoinRepository
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Constants
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _getCoinDetail = MutableStateFlow<Resource<CoinDetail>>(Resource.Loading())
    val getCoinDetail = _getCoinDetail.asStateFlow()

    init {
        getCoinDetail()
    }

    private fun getCoinDetail() {
        _getCoinDetail.value = Resource.Loading()

        viewModelScope.launch {
            val coinDetail = repository.getCoinById(Constants.COIN_ID).toCoinDetail()

            try {
                if (coinDetail != null)
                    _getCoinDetail.value = Resource.Success(coinDetail)
                else
                    _getCoinDetail.value = Resource.Error("Nothing found..")

            } catch (e: Exception) {
                _getCoinDetail.value = Resource.Error("Failed to fetch coins: ${e.message}")
            }
        }
    }
}