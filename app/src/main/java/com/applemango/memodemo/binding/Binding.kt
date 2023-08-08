package com.applemango.memodemo.binding

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.viewmodel.Mode
import com.applemango.memodemo.viewmodel.NewViewModel

@BindingAdapter("mode")
fun setMode(view: Button, mode: Mode) {
    when (mode) {
        Mode.EDIT_MEMO -> {
            view.text = "DONE"
        }

        Mode.NEW_MEMO -> {
            view.text = "SAVE"
        }

        Mode.RESULT_MEMO -> {
            view.text = "EDIT"
        }
    }
}

@BindingAdapter("mode")
fun setMode(view: EditText, mode: Mode) {

    when (mode) {
        Mode.EDIT_MEMO -> {
            view.isEnabled = true
        }

        Mode.NEW_MEMO -> {
            view.isEnabled = true
        }

        Mode.RESULT_MEMO -> {
            view.isEnabled = false
        }
    }
}