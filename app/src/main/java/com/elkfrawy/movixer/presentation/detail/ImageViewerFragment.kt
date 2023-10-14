package com.elkfrawy.movixer.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentImageViewerBinding
import com.elkfrawy.movixer.presentation.detail.adapter.ImageAdapter
import com.elkfrawy.movixer.presentation.detail.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageViewerFragment : Fragment() {

    lateinit var binding: FragmentImageViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val list = ImageViewerFragmentArgs.fromBundle(requireArguments()).images
        binding.imagePager.adapter = PagerAdapter(requireContext(), list.toList())

    }

}
