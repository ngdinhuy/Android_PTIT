package com.example.bt1.bth2.adapter

import android.app.AlertDialog
import android.app.job.JobInfo
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bt1.R
import com.example.bt1.bth2.JobModel
import com.example.bt1.bth2.SQLHelper

class MyAdapter(
    var list: ArrayList<JobModel>,
    val context: Context
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var onItemClick: OnItemClick? = null

    fun addOnItemClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_bai_thuc_hanh_2, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val job = list[position]
        holder.apply {
            tvID.text = job.id.toString()
            tvNoiDung.text = job.ten
            tvNgay.text = job.ngay
            tvTinhTrang.text = job.tinhTrang
            tvCongTac.text = job.congTac
        }
        holder.btnDelete.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context).apply {
                setTitle("Thong bao xoa")
                setMessage("Ban co chac chan xoa?")
                setIcon(R.drawable.ic_baseline_add_alert_24)
                setNegativeButton(
                    "NO"
                ) { p0, _ ->
                    p0.dismiss()
                }
                setPositiveButton(
                    "YES"
                ) { _, _ ->
                    val sqlHelper = SQLHelper(context)
                    job.id?.let { it1 -> sqlHelper.delete(it1) }
                    list = sqlHelper.getAllJob()
                    notifyDataSetChanged()
                }
            }.show()
        }
        holder.clItem.setOnClickListener{
            onItemClick?.itemClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(
        var itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var tvID: TextView
        var tvTen: TextView
        var tvNoiDung: TextView
        var tvNgay: TextView
        var tvTinhTrang: TextView
        var tvCongTac: TextView
        var btnDelete: Button
        var clItem : ConstraintLayout

        init {
            tvID = itemView.findViewById(R.id.tv_id)
            tvTen = itemView.findViewById(R.id.tv_ten)
            tvNoiDung = itemView.findViewById(R.id.tv_noi_dung)
            tvNgay = itemView.findViewById(R.id.tv_ngay)
            tvTinhTrang = itemView.findViewById(R.id.tv_tinh_trang)
            tvCongTac = itemView.findViewById(R.id.tv_cong_tac)
            btnDelete = itemView.findViewById(R.id.btn_delete)
            clItem = itemView.findViewById(R.id.cl_item)
        }
    }

    interface OnItemClick{
        fun itemClick(job: JobModel)
    }
}