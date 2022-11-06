package com.badabusiness.demo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String,
    var description: String,
    var imageUrl: String?=null,
    var editTag:Boolean=false,
    var updatedDate: Date= Date(),
    val createdDate :Date = Date()
):Parcelable