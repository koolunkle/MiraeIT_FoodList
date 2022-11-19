package com.example.food.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = ArrayList<Fragment>()

    fun setFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}