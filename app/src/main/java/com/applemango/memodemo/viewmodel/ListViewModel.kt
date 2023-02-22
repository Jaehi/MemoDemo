package com.applemango.memodemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.applemango.memodemo.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo : MemoRepositoryImpl) : ViewModel() {


    private val _memoList = repo.getAllMemo().asLiveData()
    val memoList : LiveData<List<MemoData>> = _memoList


    fun delete(title : String, contents : String, id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(MemoData(title,contents, id))
        }
    }

}