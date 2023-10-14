package com.elkfrawy.movixer.presentation.seeAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.elkfrawy.movixer.databinding.FragmentSeeAllBinding
import com.elkfrawy.movixer.presentation.utlis.AIRING_TODAY_LIST
import com.elkfrawy.movixer.presentation.utlis.NOW_PLAYING_LIST
import com.elkfrawy.movixer.presentation.utlis.ON_THE_AIR_LIST
import com.elkfrawy.movixer.presentation.utlis.POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.SERIES_POPULAR_LIST
import com.elkfrawy.movixer.presentation.utlis.SERIES_TOP_RATED_LIST
import com.elkfrawy.movixer.presentation.utlis.TOP_RATED_LIST
import com.elkfrawy.movixer.presentation.utlis.UP_COMING_LIST
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : Fragment(), SeeAllMovieAdapter.OnSeeAllMoviesItemClick,
    SeeAllSeriesAdapter.OnSeeAllSeriesItemClick {

    lateinit var binding: FragmentSeeAllBinding
    private val viewModel: SeeAllViewModel by viewModels()
    private val seeAllMovieAdapter: SeeAllMovieAdapter by lazy { SeeAllMovieAdapter(requireContext(), this) }
    private val seeAllSeriesAdapter: SeeAllSeriesAdapter by lazy { SeeAllSeriesAdapter(requireContext(), this) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = SeeAllFragmentArgs.fromBundle(requireArguments()).movieList

        when(list){
            POPULAR_LIST -> {
                loadMovieRv("Popular")
                lifecycleScope.launch {
                    viewModel.loadMovieList(POPULAR_LIST).collectLatest {
                        showResult()
                        seeAllMovieAdapter.submitData(it)
                    }
                }
            }
            UP_COMING_LIST ->{

                loadMovieRv("Up Coming")
                lifecycleScope.launch {
                    viewModel.loadMovieList(UP_COMING_LIST).collectLatest {
                        showResult()
                        seeAllMovieAdapter.submitData(it)
                    }
                }
            }
            TOP_RATED_LIST ->{
                loadMovieRv("Top Rated")
                lifecycleScope.launch {
                    viewModel.loadMovieList(TOP_RATED_LIST).collectLatest {
                        showResult()
                        seeAllMovieAdapter.submitData(it)
                    }

                }
            }
            NOW_PLAYING_LIST -> {

                loadMovieRv("Now Playing")
                lifecycleScope.launch {
                    viewModel.loadMovieList(NOW_PLAYING_LIST).collectLatest {
                        showResult()
                        seeAllMovieAdapter.submitData(it)
                    }
                }
            }
            AIRING_TODAY_LIST->{
                loadSeriesRv("Airing Today")
                lifecycleScope.launch {
                    viewModel.loadSeriesList(AIRING_TODAY_LIST).collectLatest {
                        showResult()
                        seeAllSeriesAdapter.submitData(it)
                    }
                }
            }
            ON_THE_AIR_LIST->{
                loadSeriesRv("On The Air")
                lifecycleScope.launch {
                    viewModel.loadSeriesList(ON_THE_AIR_LIST).collectLatest {
                        showResult()
                        seeAllSeriesAdapter.submitData(it)
                    }
                }
            }
            SERIES_POPULAR_LIST->{
                loadSeriesRv("Popular")
                lifecycleScope.launch {
                    viewModel.loadSeriesList(SERIES_POPULAR_LIST).collectLatest {
                        showResult()
                        seeAllSeriesAdapter.submitData(it)
                    }
                }
            }
            SERIES_TOP_RATED_LIST->{
                loadSeriesRv("Top Rated")
                lifecycleScope.launch {
                    viewModel.loadSeriesList(SERIES_TOP_RATED_LIST).collectLatest {
                        showResult()
                        seeAllSeriesAdapter.submitData(it)
                    }
                }
            }
        }

        binding.seeAllToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun loadMovieRv(title: String){

        binding.seeAllToolBar.title = title
        binding.seeAllRv.apply {
            setHasFixedSize(true)
            adapter = seeAllMovieAdapter
        }

    }

    private fun loadSeriesRv(title: String){

        binding.seeAllToolBar.title = title
        binding.seeAllRv.apply {
            setHasFixedSize(true)
            adapter = seeAllSeriesAdapter
        }

    }

    private fun showResult(){
        binding.resultPb.hide()
        binding.seeAllRv.show()
    }

    override fun setOnSeeAllMoviesClickedListener(movieId: Int) {
        val direction = SeeAllFragmentDirections.actionSeeAllFragmentToDetailFragment(movieId)
        findNavController().navigate(direction)

    }

    override fun setOnSeeAllSeriesClickedListener(seriesId: Int) {
        val direction = SeeAllFragmentDirections.actionSeeAllFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(direction)
    }

}