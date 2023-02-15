package com.example.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp2.R
import com.example.domain.entity.CoinPriceInfo
import com.example.data.utils.getFormattedLastUpdateTime
import com.example.data.utils.getFullImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_on_list.view.*

class AdapterOfCoins() : ListAdapter<CoinPriceInfo, CoinInfoViewHolder>(CoinsDiffCallback()) {

    lateinit var coinCardClickListener: (CoinPriceInfo) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_on_list, parent, false)
        return CoinInfoViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPriceInfo = getItem(position)
        with(holder) {
            textViewNameOfCoin.text = coinPriceInfo.fromSymbol + '/' + coinPriceInfo.toSymbol
            textViewPriceOfCoin.text = coinPriceInfo.price
            textViewLatestUpdate.text = holder.itemView.context.getString(R.string._latestUpdateTime) + " ${coinPriceInfo.lastUpdate}"

            Picasso.get().load(coinPriceInfo.imageUrl).into(imageViewCoin)

            itemView.setOnClickListener {
                coinCardClickListener(coinPriceInfo)
            }
        }

    }


}