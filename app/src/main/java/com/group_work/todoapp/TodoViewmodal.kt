package com.group_work.todoapp


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModal (application: Application) :AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    val allTodo : LiveData<List<Todo>>
    val repository : ToDoRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = TodoDatabase.getDatabase(application).getToDoDao()
        repository = ToDoRepository(dao)
        allTodo = repository.allTodo
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote (todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateNote(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}