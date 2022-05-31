package com.group_work.todoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {
    val allTodo: LiveData<List<Todo>>
    val repository: ToDoRepository

    init {
        val dao = TodoDatabase.getDatabase(application).getToDoDao()
        repository = ToDoRepository(dao)
        allTodo = repository.allTodo
    }
    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(todo)

    }

    fun updateTodo(todo: Todo)= viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }
    fun addTodo(todo: Todo)= viewModelScope.launch(Dispatchers.IO){
            repository.update(todo)
    }
}