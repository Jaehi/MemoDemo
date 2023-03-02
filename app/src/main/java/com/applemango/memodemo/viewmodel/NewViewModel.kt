package com.applemango.memodemo.viewmodel

import androidx.lifecycle.*
import com.applemango.memodemo.repository.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(private val repo : MemoRepositoryImpl) : ViewModel() {

    private var _resultData = MutableStateFlow<MemoData?>(null)
    val resultData : StateFlow<MemoData?> get() = _resultData

    private var _tempData = MutableStateFlow<ResultData?>(null)
    private val tempData : StateFlow<ResultData?> get() = _tempData

    private var _mode = MutableStateFlow<Mode?>(null)
    val mode : StateFlow<Mode?> get() = _mode

    private fun insert() {

        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(MemoData(tempData.value?.title.toString() , tempData.value?.content.toString()))
        }

    }

    private fun update(){
        viewModelScope.launch(Dispatchers.IO){
            if (resultData.value?.id != null){
                repo.update(MemoData(tempData.value?.title.toString(),tempData.value?.content.toString(), resultData.value?.id!!))
                repo.getMemo(resultData.value!!.id).collect{
                    _resultData.value = it
                }
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