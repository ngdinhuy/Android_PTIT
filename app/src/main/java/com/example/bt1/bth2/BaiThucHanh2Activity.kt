package com.example.bt1.bth2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.bt1.R
import com.example.bt1.bth2.adapter.MyPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BaiThucHanh2Activity : AppCompatActivity() {
    lateinit var  viewpager: ViewPager
    lateinit var bottomNav : BottomNavigationView
    lateinit var btnAdd : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_thuc_hanh2)
        viewpager = findViewById(R.id.view_pager)
        bottomNav = findViewById(R.id.bottom_nav)
        btnAdd = findViewById(R.id.floating_button_add)
        val myPagerAdapter = MyPagerAdapter(supportFragmentManager,3)
        viewpager.adapter = myPagerAdapter
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position==0) {
                    bottomNav.menu.findItem(R.id.mList).isChecked = true
                }else if (position == 1){
                    bottomNav.menu.findItem(R.id.mInfo).isChecked = true
                }else{
                    bottomNav.menu.findItem(R.id.mSearch).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        bottomNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.mList -> viewpager.currentItem = 0
                    R.id.mInfo -> viewpager.currentItem = 1
                    R.id.mSearch -> viewpager.currentItem = 2
                }
                false
            })

        btnAdd.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}