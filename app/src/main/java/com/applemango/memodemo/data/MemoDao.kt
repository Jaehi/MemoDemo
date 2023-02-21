package com.applemango.memodemo.data

import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface MemoDao {

    @Query("SELECT * FROM table_memo")
    fun getListData() : kotlinx.coroutines.flow.Flow<List<MemoData>>

    @Query("SELECT * FROM table_memo WHERE id = :id LIMIT 1")
    fun loadNewMemo(id : Int) : MemoData

    @Insert
    fun insert(vararg memoData: MemoData)

    @Delete
    fun delete(vararg memoData: MemoData)

    @Update
    fun update(vararg memoData: MemoData)
}