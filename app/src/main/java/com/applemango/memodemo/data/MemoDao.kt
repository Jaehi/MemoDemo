package com.applemango.memodemo.data

import androidx.room.*

@Dao
interface MemoDao {

    @Query("SELECT * FROM table_memo")
    fun getListData() : List<MemoData>

    @Insert
    fun insert(memoData: MemoData)

    @Delete
    fun delete(memoData: MemoData)

    @Update
    fun update(vararg memoData: MemoData)
}