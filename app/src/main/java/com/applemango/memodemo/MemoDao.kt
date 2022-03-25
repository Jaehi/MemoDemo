package com.applemango.memodemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface MemoDao {

    @Query("SELECT * FROM table_memo")
    fun getData():LiveData<List<MemoData>>

    @Insert
    fun insert(memoData: MemoData)

    @Update
    fun update(memoData: MemoData)

    @Delete
    fun delete(memoData: MemoData)

}