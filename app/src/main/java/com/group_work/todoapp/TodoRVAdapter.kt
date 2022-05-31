package com.group_work.todoapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoRVAdapter(
    val context: Context,
    val todoClickDeleteInterface: MainActivity,
    val todoClickInterface: MainActivity
) :
    RecyclerView.Adapter<TodoRVAdapter.ViewHolder>() {
    // on below line we are creating a
    // variable for our all notes list.
    private val allTodo = ArrayList<Todo>()
    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val todoTV = itemView.findViewById<TextView>(R.id.idToDoTitle)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idTvDelete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        holder.todoTV.setText(allTodo.get(position).toDoTitle)
        holder.dateTV.setText("Last Updated : " + allTodo.get(position).timeStamp)
        // on below line we are adding click listener to our delete image view icon.
        holder.deleteIV.setOnClickListener {
            // on below line we are calling a note click
            // interface and we are passing a position to it.
            todoClickDeleteInterface.onDeleteIconClick(allTodo.get(position))
        }
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            todoClickInterface.onTodoClick(allTodo.get(position))
        }
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allTodo.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<Todo>) {
        // on below line we are clearing
        // our notes array list
        allTodo.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allTodo.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}

interface TodoClickDeleteInterface {
    // creating a method for click
    // action on delete image view.
    fun onDeleteIconClick(todo: Todo)
}

interface TodoClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onNoteClick(todo: Todo)
}