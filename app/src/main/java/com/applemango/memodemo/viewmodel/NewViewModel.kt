package com.applemango.memodemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.data.MemoDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewViewModel(application: Application) : AndroidViewModel(application) {

    val db = Room.databaseBuilder(application, MemoDataBase::class.java, "db-memo").build()
    val activityEvents = MutableLiveData<ActivityEvents?>(null)
    var title = ""
    var contents = ""

    fun insert() {

        viewModelScope.launch(Dispatchers.IO) {
            when {
                title.isEmpty() -> event(ActivityEvents.Something("제목이 비엇숨"))
                contents.isEmpty() -> event(ActivityEvents.Something("내용이 비엇숨"))
            }
            if (title.isNotEmpty() && contents.isNotEmpty()) {
                db.MemoDao().insert(MemoData(title, contents))
                title = ""
                contents = ""

            }

        }
    }

    private fun event(event: ActivityEvents) {
        viewModelScope.launch(Dispatchers.Main) {
            activityEvents.value = event
        }
    }

    sealed class ActivityEvents {
        object InsertDataIsEmpty : ActivityEvents()
        data class Something(val msg: String) : ActivityEvents()
    }
}