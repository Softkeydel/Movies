package com.imdb.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerFragmentAdapter : FragmentStateAdapter {

    private val arrayList = ArrayList<Fragment>()

    constructor(activity: FragmentActivity) : super(activity)

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    fun addFragment(fragment: Fragment) {
        arrayList.add(fragment)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        // return your fragment that corresponds to this 'position'
        return arrayList[position]
    }
}

