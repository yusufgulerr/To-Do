package com.yusufguler.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yusufguler.todoapp.data.models.ToDoData

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun  getAllData() : LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)
    @Update
    suspend fun updateData(toDoData: ToDoData)

}