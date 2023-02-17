package com.example.presentation.activityies


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp2.R
import com.example.cryptoapp2.databinding.ActivityMainBinding
import com.example.presentation.fragments.CoinListFragment
import com.example.presentation.fragments.DetailInfoFragment
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLand = binding.fragmentContainerSecond != null
        launchCoinListFragment(isLand)


    }

    private fun launchCoinListFragment(isLand:Boolean) {
        val fragment = CoinListFragment.makeCoinListFragment(isLand)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

}
