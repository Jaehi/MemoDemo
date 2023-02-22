package com.applemango.memodemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.applemango.memodemo.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import com.applemango.memodemo.data.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor( private val repo : MemoRepositoryImpl) : ViewModel() {

    private var _resultData = MutableLiveData<MemoData?>()
    val resultData : MutableLiveData<MemoData?> get() = _resultData

    private var _tempData = MutableLiveData<ResultData>()
    val tempData : LiveData<ResultData> get() = _tempData

    private var _mode = MutableLiveData<Mode>()
    val mode : LiveData<Mode> get() = _mode

    private fun insert() {

        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(MemoData(tempData.value?.title.toString() , tempData.value?.content.toString()))
        }
    }

    private fun update(){
        viewModelScope.launch(Dispatchers.IO){
            if (resultData.value?.id != null){
                repo.update(MemoData(tempData.value?.title.toString(),tempData.value?.content.toString(), resultData.value?.id!!))
                val newData = repo.getMemo(tempData.value?.id!!)
//                viewModelScope.launch {
//                    //_resultData.value = newData
//                }
            }
        }
    }

    fun changeMode(mode : Mode){
        _mode.value = mode
    }

    fun setData(data : MemoData?){
       data?.let {
           _resultData.value = data
           val temp = ResultData(data.title,data.content,data.id)
           _tempData.value = temp
       }
        if (data == null){
            _resultData.value = null
            val temp = ResultData("","",null)
            _tempData.value = temp
        }

    }

    fun setTitle(title : String){
        _tempData.value?.title = title
    }

    fun setContents(contents : String){
        _tempData.value?.content = contents
    }

    fun modeAction(mode : Mode){
        when(mode){
            Mode.NEW_MEMO ->{
                insert()
                changeMode(Mode.RESULT_MEMO)
            }
            Mode.EDIT_MEMO ->{
                update()
                changeMode(Mode.RESULT_MEMO)
            }
            Mode.RESULT_MEMO ->{
                changeMode(Mode.EDIT_MEMO)
            }
        }
    }

}

enum class Mode{
    EDIT_MEMO,NEW_MEMO,RESULT_MEMO
}