package com.example.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp2.R
import com.example.cryptoapp2.databinding.CoinListFragmentBinding
import com.example.di.component
import com.example.presentation.recycler.AdapterOfCoins
import com.example.presentation.viewmodels.ListOfCoinsViewModel
import com.example.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class CoinListFragment : Fragment() {

    private var isLand by Delegates.notNull<Boolean>()

    private val binding: CoinListFragmentBinding by viewBinding()

    private val listSubcomponent by lazy {
        requireActivity().component.getCoinListSubcomponentFactory().create()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapterOfCoins: AdapterOfCoins

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[ListOfCoinsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        listSubcomponent.inject(this)
        super.onAttach(context)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLand = requireArguments().getBoolean(KEY_MODE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.coin_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCoinPriceList.adapter = adapterOfCoins

        adapterOfCoins.coinCardClickListener = {
            if (!isLand) {
                launchDetailFragmentPortrait(it.fromSymbol)
            } else {
                launchDetailFragmentLand(it.fromSymbol)
            }
        }

        lifecycleScope.launch {
            viewModel.getTopCoinsLD().observe(viewLifecycleOwner) {
                adapterOfCoins.submitList(it)
                binding.rvCoinPriceList.itemAnimator = null
                Log.d("ARSEN", "submitAdapter $it ")
            }
        }
    }


    private fun launchDetailFragmentPortrait(fsym: String) {
        val fragment = DetailInfoFragment.makeDetailInfoFragment(fsym)

        requireActivity().supportFragmentManager.beginTransaction()
            //replace нельзя ибо при возврате к этому экрану,
            //будет вызываться метод скачивания новых данных и загрузку их в бд, из за чего у юзера возникнет
            //подвисание на секунду, лучше пусть в живых будет два фрагмента, и фрагмент не придется заного создавать
            // и грузить резко новые данные при возращении
            .add(R.id.fragmentContainerView, fragment)
            .addToBackStack("DetailInfoFragment")
            .commit()

    }

    private fun launchDetailFragmentLand(fsym: String) {
        val fragment = DetailInfoFragment.makeDetailInfoFragment(fsym)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerSecond, fragment)
            .commit()
    }

    companion object {
        private const val KEY_MODE = "keyMode"
        fun makeCoinListFragment(isLand: Boolean): CoinListFragment {
            return CoinListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(KEY_MODE, isLand)
                }
            }
        }
    }

}
