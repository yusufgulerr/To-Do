package com.yusufguler.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.yusufguler.todoapp.data.ToDoDAO
import com.yusufguler.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDAO: ToDoDAO) {

    val getAllData:LiveData<List<ToDoData>> = toDoDAO.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDAO.insertData(toDoData)
    }
    suspend fun updateData(toDoData: ToDoData){
        toDoDAO.updateData(toDoData)
    }

}