package com.brain.mvvm.guide.helper.mvvm_guide.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.brain.mvvm.guide.helper.mvvm_guide.R
import com.brain.mvvm.guide.helper.mvvm_guide.adapters.CoinsAdapter
import com.brain.mvvm.guide.helper.mvvm_guide.api.presentations.CoinsViewModel
import com.brain.mvvm.guide.helper.mvvm_guide.databinding.FragmentHomeBinding
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Constants
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Constants.collectLifecycleFlow
import com.brain.mvvm.guide.helper.mvvm_guide.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val coinsViewModel: CoinsViewModel by viewModels()

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        CoinsAdapter(object : CoinsAdapter.OnItemClicked {
            override fun onClicked(id: String) {
                Constants.COIN_ID = id
                findNavController().apply {
                    if (currentDestination?.id == R.id.homeFragment)
                        navigate(R.id.coinDetailFragment)
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.collectLifecycleFlow(coinsViewModel.getAllCoins) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.isVisible = true
                }

                is Resource.Success -> {
                    binding.progressbar.isVisible = false
                    adapter.differ.submitList(it.data)
                }

                is Resource.Error -> {
                    binding.progressbar.isVisible = false
                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.recyclerview.adapter = adapter

        return binding.root
    }

}