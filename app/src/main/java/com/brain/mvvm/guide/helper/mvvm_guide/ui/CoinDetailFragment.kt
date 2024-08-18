package com.brain.mvvm.guide.helper.mvvm_guide.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.brain.mvvm.guide.helper.mvvm_guide.R
import com.brain.mvvm.guide.helper.mvvm_guide.api.presentations.CoinDetailViewModel
import com.brain.mvvm.guide.helper.mvvm_guide.databinding.FragmentCoinDetailBinding
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Constants.collectLifecycleFlow
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    private val binding by lazy {
        FragmentCoinDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: CoinDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.collectLifecycleFlow(viewModel.getCoinDetail) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.isVisible = true
                    binding.group.isVisible = false
                }

                is Resource.Success -> {
                    val item = it.data
                    with(binding) {
                        progressbar.isVisible = false
                        group.isVisible = true

                        tvName.text = item?.name ?: ""
                        tvDescription.text = item?.description ?: ""
                        tvSymbols.text = item?.symbol

                        if (item?.isActive == true)
                            isActive.text = "Active"
                        else
                            isActive.text = "Not active"
                    }

                }

                is Resource.Error -> {
                    binding.progressbar.isVisible = false
                    binding.group.isVisible = true

                }
            }
        }
    }

}