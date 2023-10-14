package com.elkfrawy.movixer.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentPersonDetailBinding
import com.elkfrawy.movixer.domain.model.Image
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.PeopleDetail
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.presentation.detail.adapter.ImageAdapter
import com.elkfrawy.movixer.presentation.home.HorizontalAdapter
import com.elkfrawy.movixer.presentation.utlis.MOVIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailFragment : Fragment(), HorizontalAdapter.OnHorizontalItemClick,
    ImageAdapter.OnImageClicked {

    var binding: FragmentPersonDetailBinding ?= null
    val personViewModel: DetailViewModel by viewModels()
    val imageAdapter: ImageAdapter by lazy { ImageAdapter(requireContext(), this) }
    val movieAdapter: HorizontalAdapter by lazy { HorizontalAdapter(MOVIES_ADAPTER, requireContext(), this) }
    val seriesAdapter: HorizontalAdapter by lazy { HorizontalAdapter(SERIES_ADAPTER, requireContext(), this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPersonDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val personId = PersonDetailFragmentArgs.fromBundle(requireArguments()).personId
        personViewModel.getAllPersonDetailsData(personId)

        personViewModel.personDetailsLiveData.observe(viewLifecycleOwner){
            setPersonDetail(it)
        }
        personViewModel.personImageLiveData.observe(viewLifecycleOwner){
            setPersonImages(it)
        }
        personViewModel.personMovieLiveData.observe(viewLifecycleOwner){
            setPersonMovies(it)
        }
        personViewModel.personSeriesLiveData.observe(viewLifecycleOwner){
            setPersonSeries(it)
        }

    }

    private fun setPersonDetail(detail: PeopleDetail){
        binding?.let {
            it.apply {
                personName.text = detail.name
                personDate.text = detail.birthday
                personBiography.text = detail.biography
                personPof.text = detail.place_of_birth
                loadImage(requireContext(), personImg, detail.profile_path)
            }
        }
    }

    private fun setPersonImages(images: List<Image>){
        if (images.isEmpty()){
            binding?.let {
                it.imageRv.hide()
                it.noImageTxt.show()
            }
        }else{
            binding?.let {
                it.imageRv.show()
                it.noImageTxt.hide()

                it.imageRv.apply {
                    setHasFixedSize(true)
                    adapter = imageAdapter
                }
                imageAdapter.submitList(images)
            }


        }

    }

    private fun setPersonMovies(movies: List<Movie>){
        if (movies.isEmpty()){
            binding?.let {
                it.movieRv.hide()
                it.noMovieTxt.show()
            }
        }else{
            binding?.let {
                it.movieRv.show()
                it.noMovieTxt.hide()

                it.movieRv.apply {
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
                movieAdapter.moviesList(movies)
            }
        }

    }

    private fun setPersonSeries(series: List<Series>){
        if (series.isEmpty()){
            binding?.let {
                it.seriesRv.hide()
                it.noSeriesTxt.show()
            }
        }else{
            binding?.let {
                it.seriesRv.show()
                it.noSeriesTxt.hide()

                it.seriesRv.apply {
                    setHasFixedSize(true)
                    adapter = seriesAdapter
                }
                seriesAdapter.seriesList(series)
                binding!!.resultPb.hide()
                binding!!.viewGroup.show()
            }
        }

    }

    override fun setOnHSeriesItemClickedListener(seriesId: Int) {
        val direction = PersonDetailFragmentDirections.actionPersonDetailFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(direction)
    }

    override fun setOnHMovieItemClickedListener(movieId: Int) {
        val direction = PersonDetailFragmentDirections.actionPersonDetailFragmentToDetailFragment(movieId)
        findNavController().navigate(direction)
    }

    override fun setOnImageClickedListener(images: List<Image>) {
        val direction = PersonDetailFragmentDirections.actionPersonDetailFragmentToImageViewerFragment(images.toTypedArray())
        findNavController().navigate(direction)
    }


}