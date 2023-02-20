package com.applemango.memodemo

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.viewmodel.Mode
import com.applemango.memodemo.viewmodel.NewViewModel

@BindingAdapter("mode","viewModel")
fun setMode(view : Button, mode : Mode, viewModel : NewViewModel){

    when(mode){
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

    view.setOnClickListener {
        when(mode){
            Mode.EDIT_MEMO -> {
                viewModel.update()
                viewModel.changeMode(Mode.RESULT_MEMO)
            }
            Mode.NEW_MEMO -> {
                viewModel.insert()
                viewModel.changeMode(Mode.RESULT_MEMO)
            }
            Mode.RESULT_MEMO -> {
                viewModel.changeMode(Mode.EDIT_MEMO)
            }
        }
    }
}

@BindingAdapter("mode","viewModel","type")
fun setMode(view : EditText , mode: Mode ,viewModel : NewViewModel , type : Int){
   if (type == 1){
       when(mode){
           Mode.EDIT_MEMO -> {
               view.isEnabled = true
               view.setText(viewModel.resultData.value?.content)
           }
           Mode.NEW_MEMO -> {
               view.isEnabled = true
           }
           Mode.RESULT_MEMO -> {
               view.isEnabled = false
               view.setText(viewModel.resultData.value?.content)
           }
       }
   }else if (type == 2){
       when(mode){
           Mode.EDIT_MEMO -> {
               view.isEnabled = true
               view.setText(viewModel.resultData.value?.title)
           }
           Mode.NEW_MEMO -> {
               view.isEnabled = true
           }
           Mode.RESULT_MEMO -> {
               view.isEnabled = false
               view.setText(viewModel.resultData.value?.title)
           }
       }
   }
}