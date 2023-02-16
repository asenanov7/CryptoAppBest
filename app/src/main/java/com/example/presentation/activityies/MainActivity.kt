package com.example.presentation.activityies


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp2.R
import com.example.cryptoapp2.databinding.ActivityMainBinding
import com.example.presentation.fragments.CoinListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchCoinListFragment()
    }

    private fun launchCoinListFragment(){
        val fragment = CoinListFragment.newInstanceEdit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

}
