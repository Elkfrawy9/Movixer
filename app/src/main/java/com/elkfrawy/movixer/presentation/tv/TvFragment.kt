package com.elkfrawy.movixer.presentation.tv

import android.media.tv.TvView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentHomeBinding
import com.elkfrawy.movixer.databinding.FragmentTvBinding
import com.elkfrawy.movixer.presentation.home.CarouselAdapter
import com.elkfrawy.movixer.presentation.home.HomeFragmentDirections
import com.elkfrawy.movixer.presentation.home.HomeViewModel
import com.elkfrawy.movixer.presentation.home.HorizontalAdapter
import com.elkfrawy.movixer.presentation.home.VerticalAdapter
import com.elkfrawy.movixer.presentation.utlis.AIRING_TODAY_LIST
import com.elkfrawy.movixer.presentation.utlis.NOW_PLAYING_LIST
import com.elkfrawy.movixer.presentation.utlis.ON_THE_AIR_LIST
import com.elkfrawy.movixer.presentation.utlis.POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.SERIES_POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.SERIES_TOP_RATED_LIST
import com.elkfrawy.movixer.presentation.utlis.TOP_RATED_LIST
import com.elkfrawy.movixer.presentation.utlis.UP_COMING_LIST
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment(), VerticalAdapter.OnVerticalItemClick, CarouselAdapter.OnItemClick,
    HorizontalAdapter.OnHorizontalItemClick {

    lateinit var upComingAdapter: VerticalAdapter
    lateinit var nowPlayingAdapter: CarouselAdapter
    lateinit var topRatedAdapter: HorizontalAdapter
    lateinit var popularAdapter: HorizontalAdapter

    private val viewModel: TvViewModel by viewModels()

    lateinit var binding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        upComingAdapter = VerticalAdapter(SERIES_ADAPTER, requireContext(), this)
        nowPlayingAdapter = CarouselAdapter(SERIES_ADAPTER, requireContext(), this)
        topRatedAdapter = HorizontalAdapter(SERIES_ADAPTER, requireContext(), this)
        popularAdapter = HorizontalAdapter(SERIES_ADAPTER, requireContext(), this)

        binding.popularSeeMore.setOnClickListener {
            navigateToSeeMore(SERIES_POPULAR_LIST)
        }

        binding.airingTodaySeeMore.setOnClickListener {
            navigateToSeeMore(AIRING_TODAY_LIST)
        }

        binding.topRatedSeeMore.setOnClickListener {
            navigateToSeeMore(SERIES_TOP_RATED_LIST)
        }

        binding.onTheAirSeeMore.setOnClickListener {
            navigateToSeeMore(ON_THE_AIR_LIST)
        }

        viewModel.onTheAirViewModel.observe(viewLifecycleOwner) {
            binding.onTheAirRv.apply {
                setHasFixedSize(true)
                adapter = upComingAdapter
            }
            upComingAdapter.seriesList(it)
        }

        viewModel.topRatedViewModel.observe(viewLifecycleOwner) {
            binding.topRatedRv.apply {
                setHasFixedSize(true)
                adapter = topRatedAdapter
            }
            topRatedAdapter.seriesList(it)
            binding.detailPb.hide()
            binding.detailGroup.show()
        }
        viewModel.popularViewModel.observe(viewLifecycleOwner) {
            binding.popularRv.apply {
                setHasFixedSize(true)
                adapter = popularAdapter
            }
            popularAdapter.seriesList(it)
        }
        viewModel.airingTodayViewModel.observe(viewLifecycleOwner) {
            binding.carouselRv.apply {
                setHasFixedSize(true)
                adapter = nowPlayingAdapter
            }
            nowPlayingAdapter.seriesList(it)
        }


    }

    private fun navigateToSeeMore(list: Int) {
        val direction = TvFragmentDirections.actionTvFragmentToSeeAllFragment(list)
        findNavController().navigate(direction)
    }

    override fun setOnSeriesItemClickedListener(seriesId: Int) {
        val direction = TvFragmentDirections.actionTvFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(direction)
    }

    override fun setOnHSeriesItemClickedListener(seriesId: Int) {
        val direction = TvFragmentDirections.actionTvFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(direction)
    }

    override fun setOnVSeriesItemClickedListener(seriesId: Int) {
        val direction = TvFragmentDirections.actionTvFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(direction)
    }

}