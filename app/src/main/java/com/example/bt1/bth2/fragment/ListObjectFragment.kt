package com.example.bt1.bth2.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bt1.R
import com.example.bt1.bth2.EditActivity
import com.example.bt1.bth2.JobModel
import com.example.bt1.bth2.SQLHelper
import com.example.bt1.bth2.adapter.MyAdapter

class ListObjectFragment : Fragment(), MyAdapter.OnItemClick {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<JobModel>
    lateinit var sqlHelper: SQLHelper
    lateinit var myAdapter : MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sqlHelper = SQLHelper(requireContext())
        initView(view)
    }

    private fun initView(view:View) {
        recyclerView = view.findViewById(R.id.recycleview)
        list = sqlHelper.getAllJob()
//        list.add(JobModel(1,"Ten","Noi dung","Ngay",1,1))
        Log.e("Size",list.size.toString())
        myAdapter = MyAdapter(list, requireContext())
        myAdapter.addOnItemClick(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }
    }

    override fun itemClick(job: JobModel) {
        val intent = Intent(activity, EditActivity::class.java)
        intent.putExtra("item",job)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        myAdapter.list = sqlHelper.getAllJob()
        myAdapter.notifyDataSetChanged()
    }
}