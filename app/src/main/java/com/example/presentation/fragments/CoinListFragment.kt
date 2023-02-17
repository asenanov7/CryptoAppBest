package com.example.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp2.R
import com.example.cryptoapp2.databinding.CoinListFragmentBinding
import com.example.presentation.recycler.AdapterOfCoins
import com.example.presentation.viewmodels.ListOfCoinsViewModel
import kotlinx.coroutines.launch

class CoinListFragment : Fragment() {



    private var _binding: CoinListFragmentBinding? = null
    private val binding: CoinListFragmentBinding
        get() = _binding ?: throw Exception("CoinListFragment == null")

    private val viewModel by lazy { ViewModelProvider(requireActivity())[ListOfCoinsViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        _binding = CoinListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterOfCoins = AdapterOfCoins()
        binding.rvCoinPriceList.adapter = adapterOfCoins

        adapterOfCoins.coinCardClickListener = {
            launchDetailFragment(it.fromSymbol)
        }


        lifecycleScope.launch {
            viewModel.getTopCoinsLD().observe(viewLifecycleOwner) {
                adapterOfCoins.submitList(it)
                binding.rvCoinPriceList.smoothScrollToPosition(0)
                Log.d("ARSEN", "submitAdapter $it ")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchDetailFragment(fsym:String){
        val fragment = DetailInfoFragment.newInstanceEdit(fsym)

        requireActivity().supportFragmentManager.beginTransaction()
            //replace нельзя ибо при возврате к этому экрану,
            //будет вызываться метод скачивания новых данных и загрузку их в бд, из за чего у юзера возникнет
            //подвисание на секунду, лучше пусть в живых будет два фрагмента, и фрагмент не придется заного создавать
            // и грузить резко новые данные при возращении
            .add(R.id.fragmentContainerView, fragment)
            .addToBackStack("DetailInfoFragment")
            .commit()

    }

    companion object{
        fun launchCoinListFragment(): CoinListFragment {
            return CoinListFragment()
        }
    }
}