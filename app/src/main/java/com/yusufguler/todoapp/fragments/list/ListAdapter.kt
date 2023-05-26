package com.yusufguler.todoapp.fragments.list

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yusufguler.todoapp.R
import com.yusufguler.todoapp.data.models.Priority
import com.yusufguler.todoapp.data.models.ToDoData
import com.yusufguler.todoapp.databinding.RowLayoutBinding
import java.util.zip.Inflater

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

     private var dataList = emptyList<ToDoData>()

    class MyViewHolder( val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }
    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}