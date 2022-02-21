package com.applemango.memodemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
//
//    class Factory(val application: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return MainViewModel(application) as T
//        }
//    }

    private val db = Room.databaseBuilder(
        application,
        MemoDataBase::class.java, "db-memo"
    ).build()

    var memolist : LiveData<List<MemoData>>


    private lateinit var titleItem : MutableLiveData<String>

    private lateinit var contentsItem : MutableLiveData<String>

    var title = ""
    var contents = ""

    init {
        memolist = getData()
    }

    fun getData(): LiveData<List<MemoData>> {
        return db.MemoDao().getData()

    }

     fun insert(title: String?, content: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            db.MemoDao().insert(MemoData(title.toString(), content.toString()))
            Log.d("00000000000","${db.MemoDao().getData()}")
        }
    }

    fun onClick(){
         titleItem.value = title
         contentsItem.value = contents
    }
}