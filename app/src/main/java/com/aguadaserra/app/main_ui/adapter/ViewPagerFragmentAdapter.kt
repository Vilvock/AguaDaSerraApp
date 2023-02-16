package com.aguadaserra.app.main_ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, list: List<Fragment>) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val list2 = list

    override fun getCount(): Int {
        return list2.size
    }

    override fun getItem(position: Int): Fragment {
        return list2[position]
    }
}