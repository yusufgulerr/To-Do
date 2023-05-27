package com.yusufguler.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.yusufguler.todoapp.data.ToDoDAO
import com.yusufguler.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDAO: ToDoDAO) {

    val getAllData:LiveData<List<ToDoData>> = toDoDAO.getAllData()
    val sortByHighPriority : LiveData<List<ToDoData>> = toDoDAO.sortByHighPriority()
    val sortByLowPriority : LiveData<List<ToDoData>> = toDoDAO.sortByLowPriority()
    suspend fun insertData(toDoData: ToDoData) {
        toDoDAO.insertData(toDoData)
    }
    suspend fun updateData(toDoData: ToDoData){
        toDoDAO.updateData(toDoData)
    }
    suspend fun deleteData(toDoData: ToDoData){
        toDoDAO.deleteItem(toDoData)
    }
    suspend fun deleteAll(){
        toDoDAO.deleteAll()
    }
    fun searchDatabase(searchQuery: String):LiveData<List<ToDoData>>{
        return toDoDAO.searchDatabase(searchQuery)
    }

}