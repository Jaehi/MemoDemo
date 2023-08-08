package com.applemango.memodemo.dialog

import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.applemango.memodemo.R
import com.applemango.memodemo.databinding.ToastMemoBinding

object MemoToast {

    fun makeToast(context : Context, message : String, seeLong : Boolean) : Toast{
        val inflater = LayoutInflater.from(context)
        val binding :ToastMemoBinding = DataBindingUtil.inflate(inflater, R.layout.toast_memo,null,false)
        binding.tvText.text = message
        val _duration = if(seeLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT

        return Toast(context).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 16.toPx())
            duration = _duration
            view = binding.root
        }
    }

    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}