package com.example.sumatifroom_ivan_arifin_ilham_genap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_adapter.view.*

class AdapterActivity (private val barangs:ArrayList<tb_barang>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<AdapterActivity.BarViewHolder>(){
    class BarViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarViewHolder {
        return BarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter,parent, false)
        )
    }

    override fun onBindViewHolder(holder: BarViewHolder, position: Int) {
        val barang = barangs [position]
        holder.view.Tid.text= barang.id.toString()
        holder.view.Tnama.text= barang.namabarang

        holder.view.Tid.setOnClickListener {
            listener.onClick(barang)
        }
        holder.view.Tnama.setOnClickListener {
            listener.onClick(barang)
        }
        holder.view.imgEdit.setOnClickListener{
            listener.onUpdate(barang)
        }
        holder.view.imgDelete.setOnClickListener{
            listener.onDelete(barang)
        }
    }

    override fun getItemCount() = barangs.size

    fun setData(list: List<tb_barang>){
        barangs.clear()
        barangs.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(tbbar : tb_barang)
        fun onUpdate(tbbar: tb_barang)
        fun onDelete(tbbar: tb_barang)

    }
}
