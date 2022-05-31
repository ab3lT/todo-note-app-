package com.group_work.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var viewModal: TodoViewModal
    lateinit var todoRV: RecyclerView
    lateinit var addFab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoRV = findViewById(R.id.idRVTodo)
        addFab = findViewById(R.id.idFABAddTo)
        todoRV.layoutManager = LinearLayoutManager(this)

        var todoRVAdapter = TodoRVAdapter(this, this, this)
        todoRV.adapter = todoRVAdapter
        viewModal = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TodoViewModal::class.java)
        viewModal.allTodo.observe (this, { list ->
            list?.let{
                todoRVAdapter.updateList(it)
        }
        })
        addFab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditTodoActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    fun onTodoClick(todo:Todo){
        val intent = Intent(this@MainActivity, AddEditTodoActivity::class.java)
        intent.putExtra("todoType", "Edit")
        intent.putExtra("todoTitle", todo.toDoTitle)
        intent.putExtra("todoDescription", todo.toDoDescription)
        intent.putExtra("todoId", todo.id)
        startActivity(intent)
        this.finish()

    }
    fun onDeleteIconClick(todo: Todo) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModal.deleteNote(todo)
        // displaying a toast message
        Toast.makeText(this, "${todo.toDoTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}