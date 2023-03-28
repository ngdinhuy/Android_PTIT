package com.example.bt1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.bt1.R
import java.util.*

class SpinnerAdapter(
    val context:Context,
    val list: ArrayList<Int>
):BaseAdapter() {
    init {
        list.add(R.drawable.son_1)
        list.add(R.drawable.son_2)
        list.add(R.drawable.son_3)
    }
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_spinner,null)
        val ivCat = view.findViewById<ImageView>(R.id.img)
        ivCat.setImageResource(list[p0])
        return view
    }
}