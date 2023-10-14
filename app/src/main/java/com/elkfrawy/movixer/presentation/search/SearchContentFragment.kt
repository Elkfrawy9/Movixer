package com.elkfrawy.movixer.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentSearchContentBinding
import com.elkfrawy.movixer.presentation.utlis.SEARCH_TEXT_KEY
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchContentFragment : Fragment() {

    lateinit var binding: FragmentSearchContentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val searchedText = requireArguments().getString(SEARCH_TEXT_KEY) ?: ""

        binding.searchViewPager.adapter = SearchPagerAdapter(this, "")

        TabLayoutMediator(binding.searchTab, binding.searchViewPager){ tab, position->
            when(position){
                0 -> tab.text = "Movie"
                1 -> tab.text = "Tv Show"
                2 -> tab.text = "People"
            }
        }.attach()


    }


}