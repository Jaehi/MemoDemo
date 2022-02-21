package com.applemango.memodemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_memo")
data class MemoData(

        val title: String,
        val content: String,
        @PrimaryKey(autoGenerate = true)
        var id : Int = 0

        )
