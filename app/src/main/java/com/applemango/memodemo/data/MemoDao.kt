package com.applemango.memodemo.data

import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface MemoDao {

    @Query("SELECT * FROM table_memo")
    suspend fun getListData() : List<MemoData>

    @Query("SELECT * FROM table_memo WHERE id = :id LIMIT 1")
    suspend fun loadNewMemo(id : Int) : MemoData

    @Insert
    suspend fun insert(vararg memoData: MemoData)

    @Delete
    suspend fun delete(vararg memoData: MemoData)

    @Update
    suspend fun update(vararg memoData: MemoData)
}