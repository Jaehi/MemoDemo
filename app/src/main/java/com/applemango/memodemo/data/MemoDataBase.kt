package com.applemango.memodemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoData::class],version = 1)
abstract class MemoDataBase : RoomDatabase() {

    abstract fun memoDao() : MemoDao


}