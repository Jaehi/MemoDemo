package com.applemango.memodemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MemoData::class], version = 2)
abstract class MemoDataBase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

}

