package com.applemango.memodemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import com.applemango.memodemo.data.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(application, MemoDataBase::class.java, "db-memo").build()

    private var _resultData = MutableLiveData<MemoData>()
    val resultData : LiveData<MemoData> get() = _resultData

    private var _tempData = MutableLiveData<ResultData>()
    val tempData : LiveData<ResultData> get() = _tempData

    private var _mode = MutableLiveData<Mode>()
    val mode : LiveData<Mode> get() = _mode

    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            db.MemoDao().insert(MemoData(tempData.value?.title.toString() , tempData.value?.content.toString()))
        }
    }

    fun update(){
        Log.d("jkldfjklfsdjkldfsjklfsd","${tempData.value} ${resultData.value} , ${mode.value}")
        viewModelScope.launch(Dispatchers.IO){
            if (resultData.value?.id != null){
                db.MemoDao().update(MemoData(tempData.value?.title.toString(),tempData.value?.content.toString(), resultData.value?.id!!))
            }
        }
    }

    fun updateData(){
        _resultData.value = null
    }

    fun changeMode(mode : Mode){
        _mode.value = mode
        Log.d("dfjsdfjkldflsjkdfjkljklsdf","${mode}")
    }

    fun setData(data : MemoData){
        _resultData.value = data
        _tempData.value?.title = data.title
        _tempData.value?.content = data.content
        Log.d("1111jkldfjklfsdjkldfsjklfsd"," ${resultData.value} , ${tempData.value?.title} , ${mode.value}")
    }

    fun setTitle(title : String){
        _tempData.value?.title = title
    }

    fun setContents(contents : String){
        _tempData.value?.content = contents
    }
}

enum class Mode{
    EDIT_MEMO,NEW_MEMO,RESULT_MEMO
}