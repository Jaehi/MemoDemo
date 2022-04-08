package com.applemango.memodemo

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(
        private val myApplication: Application
) : AndroidViewModel(myApplication) {

    val db = Room.databaseBuilder(
            myApplication,
            MemoDataBase::class.java,"db-memo"
    ).build()

    private val _memoList = MutableLiveData<List<MemoData>>()

    val memoList : LiveData<List<MemoData>> = _memoList

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = db.MemoDao().getListData()
            withContext(Dispatchers.Main){
                _memoList.value = res
            }
        }
    }

    fun delete(title : String, contents : String, id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            db.MemoDao().delete(MemoData(title,contents, id))
        }
        getData()
    }

    fun getValue(): MemoDataBase? {
        return MemoDataBase.getInstance(myApplication)
    }

}