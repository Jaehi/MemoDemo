package com.applemango.memodemo.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.applemango.memodemo.repository.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.Collections
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo: MemoRepositoryImpl) : ViewModel() {

    private val _memoList = MutableSharedFlow<List<MemoData>>(replay = 0)
    val memoList = _memoList.asSharedFlow()

    suspend fun refreshData() {
        viewModelScope.launch(Dispatchers.Default) {
            repo.getAllMemo().collect {
                if (it != null) {
                    _memoList.emit(it)
                    Log.e("===========","$it")
                }
            }
        }
    }

    fun delete(title: String, contents: String,date : String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(MemoData(title, contents, date ,id))
            refreshData()
        }
    }
}