package com.example.bt1

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import com.example.bt1.adapter.*
import com.example.bt1.adapter.SpinnerAdapter
import com.example.bt1.model.CatInfo
import kotlin.collections.ArrayList

class Bai2_Activity : AppCompatActivity(), CatItemListener, SearchView.OnQueryTextListener {
    lateinit var spinner: Spinner
    lateinit var btnAdd: Button
    lateinit var btnUpdate: Button
    lateinit var edtName: EditText
    lateinit var edtPrice: EditText
    lateinit var edtDescribe: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var rvAdapter: RecycleViewAdapter
    lateinit var searchView: androidx.appcompat.widget.SearchView
    var data = ArrayList<Int>()
    var dataCats = ArrayList<CatInfo>()
    var positionUpdate:Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai2)
        initView()
        setUpdataSpinner()
        setUpAdapterSpinner()
        setUpEvent()
        setUpAdapterRecyclerView()
    }

    private fun setUpAdapterRecyclerView() {
        dataCats.add(CatInfo(R.drawable.son_1, "NMT", "120000", "hi hi"))
        dataCats.add(CatInfo(R.drawable.son_2, "NDH", "120000", "hi hi"))
        dataCats.add(CatInfo(R.drawable.son_3, "NMH", "120000", "hi hi"))
        dataCats.add(CatInfo(R.drawable.son_1, "TTH", "120000", "hi hi"))

        rvAdapter = RecycleViewAdapter(dataCats, this)
        rvAdapter.updateUpdateCat(this)
        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@Bai2_Activity)
        }
    }

    private fun setUpEvent() {
        btnAdd.setOnClickListener {
            if (edtName.text.isNotEmpty() && edtPrice.text.isNotEmpty() && edtDescribe.text.isNotEmpty()) {
                val cat = CatInfo(
                    data[spinner.selectedItemPosition],
                    edtName.text.toString(),
                    edtPrice.text.toString(),
                    edtDescribe.text.toString()
                )
                dataCats.add(cat)
                rvAdapter.backUpListCat?.add(cat)
                rvAdapter.notifyDataSetChanged()
                edtName.text = Editable.Factory.getInstance().newEditable("")
                edtPrice.text = Editable.Factory.getInstance().newEditable("")
                edtDescribe.text = Editable.Factory.getInstance().newEditable("")
                spinner.setSelection(0)
            }
        }

        btnUpdate.setOnClickListener {
            if (edtName.text.isNotEmpty() && edtPrice.text.isNotEmpty() && edtDescribe.text.isNotEmpty()) {
                    positionUpdate?.let {
                        dataCats[it].apply {
                            imageCat = data[spinner.selectedItemPosition]
                            name = edtName.text.toString()
                            price = edtPrice.text.toString()
                            description = edtDescribe.text.toString()
                        }
                        rvAdapter.backUpListCat?.get(it)?.apply {
                            imageCat = data[spinner.selectedItemPosition]
                            name = edtName.text.toString()
                            price = edtPrice.text.toString()
                            description = edtDescribe.text.toString()
                        }
                    }
                rvAdapter.notifyDataSetChanged()
                btnUpdate.isEnabled = false
                edtName.text = Editable.Factory.getInstance().newEditable("")
                edtPrice.text = Editable.Factory.getInstance().newEditable("")
                edtDescribe.text = Editable.Factory.getInstance().newEditable("")
                spinner.setSelection(0)
            }
        }
    }

    private fun setUpdataSpinner() {
        data.add(R.drawable.son_1)
        data.add(R.drawable.son_2)
        data.add(R.drawable.son_3)
    }

    private fun initView() {
        spinner = findViewById(R.id.spinner)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        edtName = findViewById(R.id.edt1)
        edtPrice = findViewById(R.id.edt2)
        edtDescribe = findViewById(R.id.edt3)
        recyclerView = findViewById(R.id.rvList)
        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(this)
    }

    private fun setUpAdapterSpinner() {
        val spinnerAdapter = SpinnerAdapter(this, ArrayList<Int>())
        spinner.adapter = spinnerAdapter
    }

    override fun onItemClick(view: View, position: Int) {
        val cat = dataCats[position]
        positionUpdate = position
        edtName.text = Editable.Factory.getInstance().newEditable(cat.name)
        edtPrice.text = Editable.Factory.getInstance().newEditable(cat.price)
        edtDescribe.text = Editable.Factory.getInstance().newEditable(cat.description)
        spinner.setSelection(position)
        btnUpdate.isEnabled = true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        filterFollowName(p0)
        return false
    }
    fun filterFollowName(s:String?){
        val filterList = ArrayList<CatInfo>()
        rvAdapter.backUpListCat?.forEach {
            if (it.name.toLowerCase().contains(s!!.toLowerCase())){
                filterList.add(it)
            }
            if (filterList.size>0){
                rvAdapter.listCats = filterList
                rvAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"Khong co!!!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}