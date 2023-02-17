package com.example.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.CoinPriceInfo

class CoinsDiffCallback:DiffUtil.ItemCallback<CoinPriceInfo>() {
    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }
}
