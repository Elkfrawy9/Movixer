package com.elkfrawy.movixer.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elkfrawy.movixer.databinding.FragmentSeriesDetailBinding
import com.elkfrawy.movixer.domain.model.Cast
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.domain.model.Season
import com.elkfrawy.movixer.domain.model.Series
import com.elkfrawy.movixer.domain.model.SeriesDetails
import com.elkfrawy.movixer.domain.model.Video
import com.elkfrawy.movixer.presentation.detail.adapter.CastAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.ReviewAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.SeasonAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.VideoAdapter
import com.elkfrawy.movixer.presentation.home.HorizontalAdapter
import com.elkfrawy.movixer.presentation.utlis.MOVIE_REVIEW_TYPE
import com.elkfrawy.movixer.presentation.utlis.SERIES_ADAPTER
import com.elkfrawy.movixer.presentation.utlis.SERIES_REVIEW_TYPE
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.loadImage
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesDetailFragment : Fragment(), HorizontalAdapter.OnHorizontalItemClick,
    CastAdapter.OnPersonClicked {

    lateinit var binding: FragmentSeriesDetailBinding
    val viewModel: DetailViewModel by viewModels()
    val seasonAdapter: SeasonAdapter by lazy { SeasonAdapter(requireContext()) }
    lateinit var horizontalAdapter: HorizontalAdapter
    lateinit var reviewAdapter: ReviewAdapter
    val videoAdapter: VideoAdapter by lazy { VideoAdapter(requireContext()) }
    val castAdapter: CastAdapter by lazy { CastAdapter(requireContext(), this) }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentSeriesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        horizontalAdapter = HorizontalAdapter(SERIES_ADAPTER, requireContext(), this)

        val seriesId = SeriesDetailFragmentArgs.fromBundle(requireArguments()).seriesId
        viewModel.getAllSeriesDetailsData(seriesId)

        binding.backNav.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.seriesDetailsLiveData.observe(viewLifecycleOwner){
            setSeriesData(it)
            setSeasonViewData(it.seasons)
        }
        viewModel.seriesCastsLiveData.observe(viewLifecycleOwner){
            setCastViewData(it)
        }
        viewModel.seriesRecommendationLiveData.observe(viewLifecycleOwner){
            setRecommendationViewData(it)
        }
        viewModel.seriesReviewsLiveData.observe(viewLifecycleOwner){
            setReviewData(it)

        }
        viewModel.seriesVideosLiveData.observe(viewLifecycleOwner){
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
            binding.seriesVideosRv.hide()
            binding.noVideoTxt.show()
        }else{
            binding.seriesVideosRv.show()
            binding.noVideoTxt.hide()
            binding.seriesVideosRv.apply {
                setHasFixedSize(true)
                adapter = videoAdapter
            }
            videoAdapter.submitList(video)
        }

    }

    private fun setRecommendationViewData(series: List<Series>){
        if (series.isEmpty()){
            binding.recommendRv.hide()
            binding.noRecoTxt.show()
        }else{
            binding.recommendRv.show()
            binding.noRecoTxt.hide()
            binding.recommendRv.apply {
                setHasFixedSize(true)
                adapter = horizontalAdapter
            }
            horizontalAdapter.seriesList(series)
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
        binding.detailPb.hide()
        binding.detailGroup.show()
    }

    private fun setSeasonViewData(seasons: List<Season>){
        if (seasons.isEmpty()){
            binding.seasonRv.hide()
            binding.noSeasonTxt.show()
        }else{
            binding.seasonRv.show()
            binding.noSeasonTxt.hide()

            binding.seasonRv.apply {
                setHasFixedSize(true)
                adapter = seasonAdapter
            }

            seasonAdapter.submitList(seasons)
        }
    }

    private fun setSeriesData(series: SeriesDetails){

        var genre = ""
        for (g in series.genres)
            genre += "${g.name}, "

        binding.apply {
            seriesTitle.text = series.original_name
            seriesDate.text = series.first_air_date
            seriesGenera.text = genre
            seriesOverview.text = series.overview
            loadImage(requireContext(), seriesImg, series.poster_path)
            rateTxt.text = "${series.vote_average}"
            seriesRate.rating = series.vote_average/2

            reviewSeeAll.setOnClickListener {
                val direction = SeriesDetailFragmentDirections.actionSeriesDetailFragmentToReviewFragment(series.id, SERIES_REVIEW_TYPE)
                findNavController().navigate(direction)
            }
        }

    }

    override fun setOnHSeriesItemClickedListener(seriesId: Int) {
        val direction = SeriesDetailFragmentDirections.actionSeriesDetailFragmentSelf(seriesId)
        findNavController().navigate(direction)
    }

    override fun setOnPersonClickedListener(personId: Int) {
        val direction = SeriesDetailFragmentDirections.actionSeriesDetailFragmentToPersonDetailFragment(personId)
        findNavController().navigate(direction)
    }


}