package com.applemango.memodemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoData::class],version = 1)
abstract class MemoDataBase : RoomDatabase() {

    abstract fun MemoDao() : MemoDao

    companion object{
        private var instance : MemoDataBase? = null
        @Synchronized
        fun getInstance(context: Context):MemoDataBase?{
            if(instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDataBase::class.java,
                    "database_memo"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}