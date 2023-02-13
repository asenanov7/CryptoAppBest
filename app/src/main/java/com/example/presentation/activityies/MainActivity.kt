package com.example.presentation.activityies



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp2.R
import com.example.presentation.adapters.AdapterOfCoins
import com.example.domain.entity.CoinPriceInfo
import com.example.presentation.viewmodels.CoinViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var adapterOfCoins: AdapterOfCoins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        adapterOfCoins = AdapterOfCoins()
        rvCoinPriceList.adapter = adapterOfCoins

        adapterOfCoins.bridge = object : AdapterOfCoins.CoinCardClickListener {
            override fun click(CoinPriceInfo: CoinPriceInfo){
                startActivity(DetailActivity.newIntent(this@MainActivity, CoinPriceInfo))
            }
        }

        viewModel.priceList.observe(this){
            adapterOfCoins.listOfCoinsPrice = it
        }
    }

}


/*viewModel.priceList.observe(this){
           Log.d("TAG", "$it")
       }
       viewModel.getDetailInfo("BTC").observe(this){
           Log.d("TAG", "Data on db changed: $it")*/