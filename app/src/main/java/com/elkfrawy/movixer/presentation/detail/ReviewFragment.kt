package com.elkfrawy.movixer.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentReviewBinding
import com.elkfrawy.movixer.domain.model.Review
import com.elkfrawy.movixer.presentation.detail.adapter.MNSReviewAdapter
import com.elkfrawy.movixer.presentation.utlis.MOVIE_REVIEW_TYPE
import com.elkfrawy.movixer.presentation.utlis.hide
import com.elkfrawy.movixer.presentation.utlis.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewFragment : Fragment() {

    lateinit var binding: FragmentReviewBinding
    val viewModel: DetailViewModel by viewModels()
    val mnsAdapter: MNSReviewAdapter by lazy { MNSReviewAdapter(requireContext()) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = ReviewFragmentArgs.fromBundle(requireArguments()).id
        val type = ReviewFragmentArgs.fromBundle(requireArguments()).type

        if (type == MOVIE_REVIEW_TYPE){
            lifecycleScope.launch {
                viewModel.getMovieReview(id).collectLatest {
                    setupMovieRV(it)
                }
            }
        }else{
            lifecycleScope.launch {
                viewModel.getMovieReview(id).collectLatest {
                    setupSeriesRV(it)
                }
            }
        }

        binding.reviewToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private suspend fun setupMovieRV(data: PagingData<Review>){
        binding.apply {
            reviewRv.apply {
                setHasFixedSize(true)
                adapter = mnsAdapter
            }
            showRv()
            mnsAdapter.submitData(data)
        }
    }

    private suspend fun setupSeriesRV(data: PagingData<Review>){
        binding.apply {
            reviewRv.apply {
                setHasFixedSize(true)
                adapter = mnsAdapter
            }
            showRv()
            mnsAdapter.submitData(data)
        }
    }

    private fun showRv(){
        binding.resultPb.hide()
        binding.reviewRv.show()
    }

}