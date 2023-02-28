package com.applemango.memodemo

import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoData
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun getAllMemo() : Flow<List<MemoData>?>
    fun getMemo(id : Int) : Flow<MemoData?>
    suspend fun update(memo : MemoData)
    suspend fun insert(memo : MemoData)
    suspend fun delete(memo : MemoData)
}