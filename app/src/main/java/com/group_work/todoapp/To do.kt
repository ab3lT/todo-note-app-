package com.group_work.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDoTable")
class Todo(
    @ColumnInfo(name = "title") val toDoTitle :String,
    @ColumnInfo(name = "description") val toDoDescription :String,
    @ColumnInfo(name = "timestamp") val timeStamp :String
    //@ColumnInfo(name="priority") val toDoPriority : Int)
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
