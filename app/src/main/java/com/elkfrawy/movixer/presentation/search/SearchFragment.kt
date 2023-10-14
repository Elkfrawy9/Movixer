package com.elkfrawy.movixer.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.elkfrawy.movixer.R
import com.elkfrawy.movixer.databinding.FragmentSearchBinding
import com.elkfrawy.movixer.databinding.FragmentSearchContentBinding
import com.elkfrawy.movixer.presentation.utlis.SEARCH_TEXT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edLayout.setEndIconOnClickListener {
            viewModel.textStateFlow.value = binding.edSearch.text.toString()

        }


    }
}