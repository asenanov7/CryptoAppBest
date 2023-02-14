package com.example.presentation.adapters

import android.annotation.SuppressLint
import android.util.TimeUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptoapp2.R
import com.example.data.utils.getFormattedLastUpdateTime
import com.example.data.utils.getFullImage
import com.example.domain.entity.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_on_list.view.*
import java.util.concurrent.TimeUnit

class AdapterOfCoins:Adapter<AdapterOfCoins.CoinInfoViewHolder>() {
    inner class CoinInfoViewHolder(itemView: View):ViewHolder(itemView){
        val imageViewCoin: ImageView = itemView.imageViewCoin
        val textViewNameOfCoin: TextView = itemView.textViewNameOfCoin
        val textViewPriceOfCoin: TextView = itemView.textViewPriceOfCoin
        val textViewLatestUpdate: TextView = itemView.textViewLatestUpdate
    }

    var listOfCoinsPrice:List<CoinPriceInfo> = ArrayList()
        set(data) {
            field = data
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_on_list, parent,false)
        return CoinInfoViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPriceInfo = listOfCoinsPrice[position]
        with(holder) {
            textViewNameOfCoin.text = coinPriceInfo.fromSymbol+'/'+coinPriceInfo.toSymbol
            textViewPriceOfCoin.text = coinPriceInfo.price
            textViewLatestUpdate.text = holder.itemView.context.getString(R.string._latestUpdateTime)+" "+ getFormattedLastUpdateTime(coinPriceInfo.lastUpdate)
            Picasso.get().load(getFullImage(coinPriceInfo.imageUrl)).into(imageViewCoin)

            itemView.setOnClickListener { bridge.click(coinPriceInfo) }
        }
    }

    override fun getItemCount() = listOfCoinsPrice.size

    interface CoinCardClickListener{
        fun click(CoinPriceInfo: CoinPriceInfo)
    }
    lateinit var bridge: CoinCardClickListener
}