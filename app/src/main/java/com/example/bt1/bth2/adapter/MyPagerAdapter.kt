package com.example.bt1.bth2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.bt1.bth2.fragment.InfoFragment
import com.example.bt1.bth2.fragment.ListObjectFragment
import com.example.bt1.bth2.fragment.SearchFragment

class MyPagerAdapter(val fm: FragmentManager, val totalItem: Int): FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = totalItem
    override fun getItem(position: Int): Fragment {
        if (position == 0){
            return ListObjectFragment()
        }else if(position == 1){
            return InfoFragment()
        }else{
            return SearchFragment()
        }
    }
}