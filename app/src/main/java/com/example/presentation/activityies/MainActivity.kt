package com.example.presentation.activityies


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp2.R
import com.example.presentation.adapters.AdapterOfCoins
import com.example.presentation.viewmodels.ListOfCoinsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListOfCoinsViewModel
    private lateinit var adapterOfCoins: AdapterOfCoins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ListOfCoinsViewModel::class.java]
        adapterOfCoins = AdapterOfCoins()
        rvCoinPriceList.adapter = adapterOfCoins

       adapterOfCoins.coinCardClickListener = {
            startActivity(DetailActivity.newIntent(this@MainActivity, it))
        }

        lifecycleScope.launch {
            viewModel.getTopCoinsLD().observe(this@MainActivity) {
                adapterOfCoins.submitList(it)
                Log.d("ARSEN", "submitAdapter $it ")
            }
        }
    }

}
