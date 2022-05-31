package com.group_work.todoapp

import androidx.lifecycle.LiveData

class ToDoRepository(private val todoDao:ToDoDao) {
    val allTodo:LiveData<List<Todo>> = todoDao.getAllToDo()

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }
    suspend fun update(todo: Todo){
        todoDao.update(todo)
}}