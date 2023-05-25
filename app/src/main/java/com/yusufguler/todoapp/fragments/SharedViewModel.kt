package com.yusufguler.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.yusufguler.todoapp.R
import com.yusufguler.todoapp.data.models.Priority

class SharedViewModel(application: Application):AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }
    }


    fun verifyDataFromUser(title:String,description:String):Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())
    }
    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> Priority.LOW
        }
    }
     fun parsePriorityToInt(priortiy : Priority):Int{
        return when(priortiy){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}