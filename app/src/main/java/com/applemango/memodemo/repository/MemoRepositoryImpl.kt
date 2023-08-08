package com.applemango.memodemo.repository

import android.util.Log
import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(private val memoDao: MemoDao) : MemoRepository {

    override suspend fun getAllMemo(): Flow<List<MemoData>?> {
        return flow {
            try {
                emit(memoDao.getListData())
            } catch (e: Exception) {
                emit(null)
            }

        }
    }

    override suspend fun getMemo(id: Int): Flow<MemoData?> {
        return flow {
            try {
                emit(memoDao.loadNewMemo(id))
            } catch (e: Exception) {
                emit(null)
            }
        }
    }

    override suspend fun update(memo: MemoData) {
        memoDao.update(memo)
    }

    override suspend fun insert(memo: MemoData) {
        memoDao.insert(memo)
    }

    override suspend fun delete(memo: MemoData) {
        memoDao.delete(memo)
    }
}