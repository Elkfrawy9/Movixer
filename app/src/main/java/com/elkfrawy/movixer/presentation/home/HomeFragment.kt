package com.elkfrawy.movixer.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentHomeBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.presentation.utlis.MOVIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.NOW_PLAYING_LIST
import com.elkfrawy.movixer.presentation.utlis.POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.TOP_RATED_LIST
import com.elkfrawy.movixer.presentation.utlis.UP_COMING_LIST
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), VerticalAdapter.OnVerticalItemClick,
    HorizontalAdapter.OnHorizontalItemClick, CarouselAdapter.OnItemClick {

    lateinit var binding: FragmentHomeBinding
    lateinit var upComingAdapter: VerticalAdapter
    lateinit var nowPlayingAdapter: CarouselAdapter
    lateinit var topRatedAdapter: HorizontalAdapter
    lateinit var popularAdapter: HorizontalAdapter

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upComingAdapter = VerticalAdapter(MOVIES_ADAPTER, requireContext(), this)
        topRatedAdapter = HorizontalAdapter(MOVIES_ADAPTER, requireContext(), this)
        popularAdapter = HorizontalAdapter(MOVIES_ADAPTER, requireContext(), this)
        nowPlayingAdapter = CarouselAdapter(MOVIES_ADAPTER, requireContext(), this)

        binding.popularSeeMore.setOnClickListener {
            navigateToSeeMore(POPULAR_LIST)
        }

        binding.nowPlayingSeeMore.setOnClickListener {
            navigateToSeeMore(NOW_PLAYING_LIST)
        }

        binding.topRatedSeeMore.setOnClickListener {
            navigateToSeeMore(TOP_RATED_LIST)

        }

        binding.upComingSeeMore.setOnClickListener {
            navigateToSeeMore(UP_COMING_LIST)
        }

        viewModel.upComingViewModel.observe(viewLifecycleOwner){
            binding.upComingRv.apply {
                setHasFixedSize(true)
                adapter = upComingAdapter
            }
            upComingAdapter.moviesList(it)
        }

        viewModel.topRatedViewModel.observe(viewLifecycleOwner){
            binding.topRatedRv.apply {
                setHasFixedSize(true)
                adapter = topRatedAdapter
            }
            topRatedAdapter.moviesList(it)
            binding.resultPb.hide()
            binding.viewGroup.show()
        }
        viewModel.popularViewModel.observe(viewLifecycleOwner){
            binding.popularRv.apply {
                setHasFixedSize(true)
                adapter = popularAdapter
            }
            popularAdapter.moviesList(it)
        }
        viewModel.nowPlayingViewModel.observe(viewLifecycleOwner){
            binding.carouselRv.apply {
                setHasFixedSize(true)
                adapter = nowPlayingAdapter
            }
            nowPlayingAdapter.moviesList(it)
        }


    }

    private fun navigateToSeeMore(list: Int){
        val direction = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(list)
        findNavController().navigate(direction)

    }

    override fun setOnMovieItemClickedListener(movieId: Int) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId)
        findNavController().navigate(direction)
    }

    override fun setOnHMovieItemClickedListener(movieId: Int) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId)
        findNavController().navigate(direction)
    }

    override fun setOnVMovieItemClickedListener(movieId: Int) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId)
        findNavController().navigate(direction)
    }

}