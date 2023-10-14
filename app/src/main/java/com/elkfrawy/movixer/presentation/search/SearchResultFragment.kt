package com.elkfrawy.movixer.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elkfrawy.movixer.databinding.FragmentSearchResultBinding
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.People
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.seeAll.SeeAllMovieAdapter
import com.elkfrawy.movixer.presentation.seeAll.SeeAllSeriesAdapter
import com.elkfrawy.movixer.presentation.utlis.SEARCH_PAGER_POSITION
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultFragment : Fragment(), SeeAllMovieAdapter.OnSeeAllMoviesItemClick,
    SeeAllSeriesAdapter.OnSeeAllSeriesItemClick, PeopleAdapter.OnPersonClickListener {

    lateinit var binding: FragmentSearchResultBinding
    val searchViewModel: SearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt(SEARCH_PAGER_POSITION, -1)
        //val text = requireArguments().getString(SEARCH_TEXT_KEY) ?: ""

        lifecycleScope.launch {

            searchViewModel.textStateFlow.collectLatest {
                if (it.isNotEmpty()) {
                    binding.resultPb.show()

                    when (position) {
                        1 -> {
                            lifecycleScope.launch {
                                searchViewModel.loadSeriesList(it).collectLatest {
                                    setSeriesResults(it)
                                }
                            }
                        }

                        2 -> {
                            lifecycleScope.launch {
                                searchViewModel.loadPeopleList(it).collectLatest {
                                    setPeopleResults(it)

                                }
                            }
                        }

                        else -> {
                            lifecycleScope.launch {
                                searchViewModel.loadMovieList(it).collectLatest {
                                    setMoviesResults(it)
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    private suspend fun setMoviesResults(data: PagingData<Movie>) {
        val movieAdapter = SeeAllMovieAdapter(requireContext(), this)
        binding.searchResultRv.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        showRv()
        movieAdapter.submitData(data)

    }

    private suspend fun setSeriesResults(data: PagingData<Series>) {

        val seriesAdapter = SeeAllSeriesAdapter(requireContext(), this)
        binding.searchResultRv.apply {
            setHasFixedSize(true)
            adapter = seriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        showRv()
        seriesAdapter.submitData(data)
    }

    private suspend fun setPeopleResults(data: PagingData<People>) {
        val peopleAdapter = PeopleAdapter(requireContext(), this)
        binding.searchResultRv.apply {
            setHasFixedSize(true)
            adapter = peopleAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        showRv()
        peopleAdapter.submitData(data)
    }

    private fun showRv() {
        binding.resultPb.hide()
        binding.searchResultRv.show()
    }

    override fun setOnSeeAllMoviesClickedListener(movieId: Int) {
        val direction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(movieId)
        findNavController().navigate(direction)
    }

    override fun setOnSeeAllSeriesClickedListener(seriesId: Int) {
        val direction =
            SearchFragmentDirections.actionSearchFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(direction)
    }

    override fun setOnPersonClickedListener(personId: Int) {
        val direction =
            SearchFragmentDirections.actionSearchFragmentToPersonDetailFragment(personId)
        findNavController().navigate(direction)
    }


}



