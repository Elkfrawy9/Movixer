package com.elkfrawy.movixer.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentDetailBinding
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Movie
import com.elkfrawy.movixer.domain.model.MovieDetails
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.model.SeriesDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.presentation.detail.adapter.CastAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.ReviewAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.SeasonAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.VideoAdapter
import com.elkfrawy.movixer.presentation.home.HorizontalAdapter
import com.elkfrawy.movixer.presentation.utlis.MOVIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.MOVIE_REVIEW_TYPE
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), HorizontalAdapter.OnHorizontalItemClick,
    CastAdapter.OnPersonClicked {

    lateinit var binding: FragmentDetailBinding
    val viewModel: DetailViewModel by viewModels()
    lateinit var recommendationAdapter: HorizontalAdapter
    lateinit var similarAdapter: HorizontalAdapter
    lateinit var reviewAdapter: ReviewAdapter
    val videoAdapter: VideoAdapter by lazy { VideoAdapter(requireContext()) }
    val castAdapter: CastAdapter by lazy { CastAdapter(requireContext(), this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendationAdapter = HorizontalAdapter(MOVIES_ADAPTER, requireContext(), this)
        similarAdapter = HorizontalAdapter(MOVIES_ADAPTER, requireContext(), this)

        val movieId = DetailFragmentArgs.fromBundle(requireArguments()).movieId
        viewModel.getAllMoviesDetailsData(movieId)

        binding.backNav.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner) {
            setMovieData(it)
        }

        viewModel.movieReviewsLiveData.observe(viewLifecycleOwner) {
            setReviewData(it)
        }

        viewModel.movieRecommendationLiveData.observe(viewLifecycleOwner) {
            setRecommendationViewData(it)
        }

        viewModel.movieSimilarLiveData.observe(viewLifecycleOwner) {
            setSimilarViewData(it)
        }
        viewModel.movieCastsLiveData.observe(viewLifecycleOwner) {
            setCastViewData(it)
        }

        viewModel.movieVideoLiveData.observe(viewLifecycleOwner) {
            setVideoViewData(it)

        }




    }

    private fun setCastViewData(cast: List<Cast>){
        if (cast.isEmpty()){
            binding.castsRv.hide()
            binding.noCastTxt.show()
        }else{
            binding.castsRv.show()
            binding.noCastTxt.hide()
            binding.castsRv.apply {
                setHasFixedSize(true)
                adapter = castAdapter
            }
            castAdapter.submitList(cast)
        }
    }

    private fun setVideoViewData(video: List<Video>){
        if (video.isEmpty()){
            binding.movieVideosRv.hide()
            binding.noVideoTxt.show()
        }else {
            binding.movieVideosRv.show()
            binding.noVideoTxt.hide()
            binding.movieVideosRv.apply {
                setHasFixedSize(true)
                adapter = videoAdapter
            }
            videoAdapter.submitList(video)
        }
        binding.detailPb.hide()
        binding.detailGroup.show()
    }

    private fun setRecommendationViewData(movie: List<Movie>){
        if (movie.isEmpty()){
            binding.recommendRv.hide()
            binding.noRecoTxt.show()
        }else{
            binding.recommendRv.show()
            binding.noRecoTxt.hide()
            binding.recommendRv.apply {
                setHasFixedSize(true)
                adapter = recommendationAdapter
            }
            recommendationAdapter.moviesList(movie)
        }

    }

    private fun setSimilarViewData(movie: List<Movie>){
        if (movie.isEmpty()){
            binding.similarRv.hide()
            binding.noSimilarTxt.show()
        }else{
            binding.similarRv.show()
            binding.noSimilarTxt.hide()
            binding.similarRv.apply {
                setHasFixedSize(true)
                adapter = similarAdapter
            }
            similarAdapter.moviesList(movie)
        }

    }

    private fun setReviewData(reviews: List<Review>){
        if (reviews.isEmpty()){
            binding.reviewsRv.hide()
            binding.reviewSeeAll.hide()
            binding.noReviewTxt.show()
        }else{
            binding.reviewsRv.show()
            binding.reviewSeeAll.show()
            binding.noReviewTxt.hide()
            reviewAdapter = ReviewAdapter(requireContext(), if (reviews.size > 3) 3 else reviews.size)
            binding.reviewsRv.apply {
                setHasFixedSize(true)
                adapter = reviewAdapter
            }
            reviewAdapter.submitList(reviews)
        }

    }

    private fun setMovieData(movie: MovieDetails){

        var genre = ""
        for (g in movie.genres)
            genre += "${g.name}, "

        binding.apply {
            movieTitle.text = movie.original_title
            movieDate.text = movie.release_date
            movieDuration.text = "${movie.runtime}"
            movieGenera.text = genre
            movieOverview.text = movie.overview
            loadImage(requireContext(), movieImg, movie.poster_path)
            rateTxt.text = "${movie.vote_average ?: 0.0}"
            movieRate.rating = movie.vote_average/2

            reviewSeeAll.setOnClickListener {
                val direction = DetailFragmentDirections.actionDetailFragmentToReviewFragment(movie.id, MOVIE_REVIEW_TYPE)
                findNavController().navigate(direction)
            }
        }

    }

    override fun setOnHSeriesItemClickedListener(seriesId: Int) {
        val direction = DetailFragmentDirections.actionDetailFragmentSelf(seriesId)
        findNavController().navigate(direction)
    }

    override fun setOnHMovieItemClickedListener(movieId: Int) {
        val direction = DetailFragmentDirections.actionDetailFragmentSelf(movieId)
        findNavController().navigate(direction)
    }

    override fun setOnPersonClickedListener(personId: Int) {
        val direction = DetailFragmentDirections.actionDetailFragmentToPersonDetailFragment(personId)
        findNavController().navigate(direction)
    }

}