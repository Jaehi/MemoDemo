package com.applemango.memodemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(
        private val myApplication: Application
) : AndroidViewModel(myApplication) {

    private val db = Room.databaseBuilder(
            myApplication,
            MemoDataBase::class.java,"db-memo"
    ).build()

    private val _memoList = db.MemoDao().getListData().asLiveData()
    val memoList : LiveData<List<MemoData>> = _memoList


    fun delete(title : String, contents : String, id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            db.MemoDao().delete(MemoData(title,contents, id))
        }
    }

    fun getValue(): MemoDataBase? {
        return MemoDataBase.getInstance(myApplication)
    }



}