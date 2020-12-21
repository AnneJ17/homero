package com.apolis.homero.ui.property

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> AddPropertyFragment()
            1 -> PropertyDetailFragment()
            else -> MortgageInformationFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}