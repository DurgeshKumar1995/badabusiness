package com.badabusiness.demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String,
    var description: String,
    var imageUrl: String?=null,
    var editTag:Boolean=false,
    var updatedDate: Date= Date(),
    val createdDate :Date = Date()
)