package com.applemango.memodemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.applemango.memodemo.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo : MemoRepositoryImpl) : ViewModel() {

    private val _memoList = MutableSharedFlow<List<MemoData>>(replay = 0)
    val memoList = _memoList.asSharedFlow()


    fun refreshData(){
        CoroutineScope(Dispatchers.IO).launch {
            repo.getAllMemo().collect{
                if (it != null) {
                    Log.d("dfs;kdf;lsakf;afdkasl;f","${it}")
                    _memoList.emit(it)
                }
            }
        }
    }

    fun delete(title : String, contents : String, id : Int) {
        Log.d("dfs;kdf;lsakf;afdkasl;f","$title $contents $id")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("dfs;kdf;lsakf;afdkasl;f","들어옴")
            repo.delete(MemoData(title,contents, id))
            refreshData()
        }
    }

}