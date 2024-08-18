package com.brain.mvvm.guide.helper.mvvm_guide.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brain.mvvm.guide.helper.mvvm_guide.api.domain.models.Coin
import com.brain.mvvm.guide.helper.mvvm_guide.databinding.ItemCoinsBinding

class CoinsAdapter(private val onItemClicked: OnItemClicked) :
    RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    interface OnItemClicked {
        fun onClicked(id: String)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    class ViewHolder(private val binding: ItemCoinsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Coin, onItemClicked: OnItemClicked) {
            with(binding) {
                tvCoinName.text = item.name
                tvRank.text = item.rank.toString()
                tvSymbols.text = item.symbol

                itemView.setOnClickListener {
                    onItemClicked.onClicked(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCoinsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onItemClicked)
    }
}