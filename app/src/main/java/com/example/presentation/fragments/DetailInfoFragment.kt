package com.example.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp2.databinding.DetailInfoFragmentBinding
import com.example.di.component
import com.example.presentation.viewmodels.DetailViewModel
import com.example.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailInfoFragment() : Fragment() {

    private var _binding: DetailInfoFragmentBinding? = null
    private val binding: DetailInfoFragmentBinding
        get() = _binding ?: throw Exception("DetailInfoFragment == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailViewModel

    private val detailSubcomponent by lazy {
        requireActivity().component.getDetailInfoSubcomponentFactory().create()
    }

    override fun onAttach(context: Context) {
        detailSubcomponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DetailInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        val coinSym = requireArguments().getString(KEY_COIN_NAME)?.let {
            lifecycleScope.launch {
                viewModel.getInfoAboutSingleCoinLD(it).observe(viewLifecycleOwner) { dynamicInfo ->
                    Picasso.get().load(dynamicInfo.imageUrl).into(binding.imageViewDetailCoin)
                    with(dynamicInfo) {
                        binding.textViewFsym.text = fromSymbol
                        binding.textViewTsym.text = toSymbol
                        binding.priceDetail.text = "Цена $price"
                        binding.minPriceOfDay.text = "Минимум за день $lowDay"
                        binding.maxPriceOfDay.text = "Максимум за день $highDay"
                        binding.latestMarket.text = "Последняя сделка на $lastMarket"
                        binding.timeOfUpdate.text = "Обновлено: $lastUpdate"
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_COIN_NAME = "FSYM"

        fun makeDetailInfoFragment(coinNameFSYM: String): DetailInfoFragment {
            return DetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_COIN_NAME, coinNameFSYM)
                }
            }
        }

    }
}