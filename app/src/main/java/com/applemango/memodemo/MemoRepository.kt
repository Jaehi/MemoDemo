package com.applemango.memodemo

import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoData
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun getAllMemo() : Flow<List<MemoData>?>
    fun getMemo(id : Int) : Flow<MemoData?>
    fun update(memo : MemoData)
    fun insert(memo : MemoData)
    fun delete(memo : MemoData)
}