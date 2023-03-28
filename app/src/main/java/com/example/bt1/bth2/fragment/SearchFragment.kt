package com.example.bt1.bth2.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bt1.R
import com.example.bt1.bth2.EditActivity
import com.example.bt1.bth2.JobModel
import com.example.bt1.bth2.SQLHelper
import com.example.bt1.bth2.adapter.MyAdapter
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<JobModel>
    lateinit var sqlHelper: SQLHelper
    lateinit var myAdapter : MyAdapter
    lateinit var searchView: androidx.appcompat.widget.SearchView
    lateinit var etStartDate: EditText
    lateinit var etEndDate: EditText
    lateinit var btnSearch: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sqlHelper = SQLHelper(requireContext())
        initView(view)
    }

    private fun initView(view:View) {
        recyclerView = view.findViewById(R.id.recycleview)
        list = sqlHelper.getAllJob()
        searchView = view.findViewById(R.id.search_view)
        etStartDate =  view.findViewById(R.id.et_start_date)
        etEndDate = view.findViewById(R.id.et_end_date)
        btnSearch = view.findViewById(R.id.btn_search)
//        list.add(JobModel(1,"Ten","Noi dung","Ngay",1,1))
        Log.e("Size",list.size.toString())
        myAdapter = MyAdapter(list, requireContext())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }

        searchView.setOnQueryTextListener(this)

        etStartDate.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                etStartDate.setText("$dayOfMonth/${monthOfYear+1}/$year")

            }, year, month, day)
            dpd.show()
        }

        etEndDate.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                etEndDate.setText("$dayOfMonth/${monthOfYear+1}/$year")

            }, year, month, day)
            dpd.show()
        }

        btnSearch.setOnClickListener{

        }
    }

    override fun onResume() {
        super.onResume()
        myAdapter.list = sqlHelper.getAllJob()
        myAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        updateSearchByTen(newText)
        return false
    }

    private fun updateSearchByTen(s: String?) {
        s?.let{
            if (it.isEmpty()){
                myAdapter.list = sqlHelper.getAllJob()
                myAdapter.notifyDataSetChanged()
            }else{
                myAdapter.list = sqlHelper.searchByName(it)
                myAdapter.notifyDataSetChanged()
            }
        }
    }
}