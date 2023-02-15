package com.example.presentation.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_coin_on_list.view.*

    class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageViewCoin: ImageView = itemView.imageViewCoin
        val textViewNameOfCoin: TextView = itemView.textViewNameOfCoin
        val textViewPriceOfCoin: TextView = itemView.textViewPriceOfCoin
        val textViewLatestUpdate: TextView = itemView.textViewLatestUpdate
    }
