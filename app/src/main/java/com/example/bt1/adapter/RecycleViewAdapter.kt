package com.example.bt1.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bt1.R
import com.example.bt1.model.CatInfo

class RecycleViewAdapter(
    var listCats : ArrayList<CatInfo>,
    val context: Context
) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {
    var updateCat : CatItemListener? = null
    var backUpListCat : ArrayList<CatInfo>? = null
    fun updateUpdateCat(catItemListener: CatItemListener){
        this.updateCat = catItemListener
    }
    init {
        backUpListCat = listCats
    }
    class ViewHolder(
        var itemView: View,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imgCat: ImageView
        var tvName: TextView
        var tvPrice: TextView
        var tvDescription: TextView
        var btnDelete: Button
        var clInfo : ConstraintLayout? = null
        init {
            imgCat = itemView.findViewById(R.id.img_cat)
            tvName = itemView.findViewById(R.id.tv_name_cat)
            tvPrice = itemView.findViewById(R.id.tv_price_cat)
            tvDescription = itemView.findViewById(R.id.tv_description_cat)
            btnDelete = itemView.findViewById(R.id.btn_delete)
            clInfo = itemView.findViewById(R.id.cl_info)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cat_bai2,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = listCats[position]
        holder.imgCat.setImageResource(cat.imageCat)
        holder.tvName.text = cat.name
        holder.tvPrice.text = cat.price
        holder.tvDescription.text = cat.description
        holder.btnDelete.setOnClickListener{
            val alertDialog = AlertDialog.Builder(context).apply {
                setTitle("Thong bao xoa")
                setMessage("Ban co chac chan xoa?")
                setIcon(R.drawable.ic_baseline_add_alert_24)
                setNegativeButton("NO"
                ) { p0, _ ->
                    p0.dismiss()
                }
                setPositiveButton("YES"
                ) { _, _ ->
                    listCats.removeAt(position)
                    backUpListCat?.removeAt(position)
                    notifyDataSetChanged()
                }
            }.show()
        }
        holder.clInfo?.setOnClickListener{
            updateCat?.onItemClick(it,position)
        }
    }

    override fun getItemCount(): Int = listCats.size
}

interface CatItemListener{
    fun onItemClick(view:View,position: Int)
}