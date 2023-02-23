package com.applemango.memodemo

import android.util.Log
import androidx.room.Room
import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor( private val memoDao: MemoDao) : MemoRepository {

    override fun getAllMemo(): Flow<List<MemoData>?> {
        return flow{
            try{
                emit(memoDao.getListData())
            }catch (e:Exception){
                emit(null)
            }

        }
    }

    override fun getMemo(id : Int): Flow<MemoData?> {
        return flow{
            try{
                emit(memoDao.loadNewMemo(id))
            }catch (e:Exception){
                emit(null)
            }

        }
    }

    override fun update(memo : MemoData) {
        Log.d("djdfslasdjkdajslk","${memo}")
        memoDao.update(memo)
    }

    override fun insert(memo : MemoData) {

        Log.d("djdfslasdjkdajslk","${memo}")
        memoDao.insert(memo)
    }

    override fun delete(memo : MemoData) {

        Log.d("djdfslasdjkdajslk","${memo}")
        memoDao.delete(memo)
    }
}