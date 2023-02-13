package com.example.presentation.activityies

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp2.R
import com.example.domain.entity.CoinPriceInfo
import com.example.presentation.viewmodels.CoinViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val coinPriceInfo = intent.getSerializableExtra("coin") as CoinPriceInfo

        val viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(coinPriceInfo.fromSymbol).observe(this) { dynamicInfo->
            Picasso.get().load(coinPriceInfo.getFullImage()).into(imageViewDetailCoin)
            with(dynamicInfo) {
                textViewFsym.text = fromSymbol
                textViewTsym.text = toSymbol
                priceDetail.text = "Цена $price"
                minPriceOfDay.text = "Минимум за день $lowDay"
                maxPriceOfDay.text = "Максимум за день $highDay"
                latestMarket.text = "Последняя сделка на $lastMarket"
                timeOfUpdate.text = "Обновлено: ${getFormattedLastUpdateTime()}"
            }
        }
    }

    companion object {
        fun newIntent(context: Context, CoinPriceInfo: CoinPriceInfo): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("coin", CoinPriceInfo)
            return intent
        }
    }
}