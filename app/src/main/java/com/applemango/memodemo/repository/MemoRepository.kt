package com.applemango.memodemo.repository

import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoData
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun getAllMemo() : Flow<List<MemoData>?>
    suspend fun getMemo(id : Int) : Flow<MemoData?>

    suspend fun getCalendarMemoList(date : String) : Flow<List<MemoData>?>
    suspend fun update(memo : MemoData)
    suspend fun insert(memo : MemoData)
    suspend fun delete(memo : MemoData)
}