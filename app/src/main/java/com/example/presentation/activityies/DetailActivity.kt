package com.example.presentation.activityies

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp2.R
import com.example.domain.entity.CoinPriceInfo
import com.example.presentation.viewmodels.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val coinPriceInfo = intent.getSerializableExtra("coin") as CoinPriceInfo

        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        lifecycleScope.launch {
            viewModel.getInfoAboutSingleCoinLD(coinPriceInfo.fromSymbol)
                .observe(this@DetailActivity) { dynamicInfo ->
                    Picasso.get().load(dynamicInfo.imageUrl).into(imageViewDetailCoin)
                    with(dynamicInfo) {
                        textViewFsym.text = fromSymbol
                        textViewTsym.text = toSymbol
                        priceDetail.text = "Цена $price"
                        minPriceOfDay.text = "Минимум за день $lowDay"
                        maxPriceOfDay.text = "Максимум за день $highDay"
                        latestMarket.text = "Последняя сделка на $lastMarket"
                        timeOfUpdate.text = "Обновлено: $lastUpdate"
                    }
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