package com.applemango.memodemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.repository.MemoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(private val repo : MemoRepositoryImpl) : ViewModel() {

    private val _memoList = MutableSharedFlow<List<MemoData>>(replay = 0)
    val memoList = _memoList.asSharedFlow()

    suspend fun getData(date : String){
        viewModelScope.launch(Dispatchers.Default) {
            repo.getCalendarMemoList(date).collect(){
                if(it != null){
                    _memoList.emit(it)
                }
            }
        }
    }
}