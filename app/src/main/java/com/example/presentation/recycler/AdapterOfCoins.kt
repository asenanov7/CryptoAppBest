package com.example.presentation.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp2.R
import com.example.cryptoapp2.databinding.ItemCoinOnListBinding
import com.example.domain.CoinPriceInfo
import com.example.presentation.recycler.AdapterOfCoins.*
import com.squareup.picasso.Picasso
import javax.inject.Inject

class AdapterOfCoins @Inject constructor() : ListAdapter<CoinPriceInfo, CoinInfoViewHolder>(CoinsDiffCallback()) {

    lateinit var coinCardClickListener: (CoinPriceInfo) -> Unit

    class CoinInfoViewHolder(val binding: ItemCoinOnListBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinOnListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPriceInfo = getItem(position)

        with(holder.binding) {
            textViewNameOfCoin.text = coinPriceInfo.fromSymbol + '/' + coinPriceInfo.toSymbol
            textViewPriceOfCoin.text = coinPriceInfo.price
            textViewLatestUpdate.text = holder.itemView.context.getString(R.string._latestUpdateTime) + " ${coinPriceInfo.lastUpdate}"

            Picasso.get().load(coinPriceInfo.imageUrl).into(imageViewCoin)

            root.setOnClickListener {
                coinCardClickListener(coinPriceInfo)
            }
        }

    }


}