package com.applemango.memodemo

import androidx.room.Room
import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MemoRepositoryImpl(private val memoDao : MemoDao) : MemoRepository {

    override fun getAllMemo(): Flow<List<MemoData>> {

//        return flow{
//            emit(listOf())
//        }

        return memoDao.getListData()
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
        memoDao.update(memo)
    }

    override fun insert(memo : MemoData) {
        memoDao.insert(memo)
    }

    override fun delete(memo : MemoData) {
        memoDao.delete(memo)
    }
}