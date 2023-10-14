package com.elkfrawy.movixer.presentation.search

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.elkfrawy.movixer.presentation.utlis.SEARCH_PAGER_POSITION
import com.elkfrawy.movixer.presentation.utlis.SEARCH_TEXT_KEY

class SearchPagerAdapter(fragment: Fragment, val text: String): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = SearchResultFragment()
        fragment.arguments = bundleOf().apply {
            putInt(SEARCH_PAGER_POSITION, position)
            putString(SEARCH_TEXT_KEY, text)
        }
        return fragment
    }
}