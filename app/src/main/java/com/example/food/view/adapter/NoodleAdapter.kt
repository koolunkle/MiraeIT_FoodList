package com.example.food.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.AdapterNoodleBinding
import com.example.food.data.DataVO

class NoodleAdapter(private val dataList: MutableList<DataVO>) :
    RecyclerView.Adapter<NoodleAdapter.NoodleViewHolder>() {

    inner class NoodleViewHolder(private val binding: AdapterNoodleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataVO) {
            binding.ivIcon.setImageResource(data.image)
            binding.tvName.text = data.name

            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onClickItem(itemView, position)
                }
            }

            binding.ivDelete.setOnLongClickListener {
                listener?.onClickDeleteItem(itemView, position)
                true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NoodleViewHolder {
        val binding =
            AdapterNoodleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoodleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoodleViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    /**
     * Interface
     */
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClickItem(view: View, position: Int)
        fun onClickDeleteItem(view: View, position: Int)
    }

}