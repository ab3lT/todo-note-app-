package com.group_work.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo){
    }

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("Select * from toDoTable order by id ASC")
    fun getAllToDo(): LiveData<List<Todo>>
}