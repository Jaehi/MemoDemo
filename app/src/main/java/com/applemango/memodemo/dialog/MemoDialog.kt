package com.applemango.memodemo.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.applemango.memodemo.databinding.DialogMemoBinding

class MemoDialog(context: Context, val deleteMemo: (() -> Unit)? = null) : Dialog(context) {

    lateinit var binding: DialogMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogMemoBinding.inflate(layoutInflater)

        binding.dialog = this

        setContentView(binding.root)

    }

    fun goDelete() {
        deleteMemo?.let { it() }
        dismiss()
    }

    fun cancle() {
        dismiss()
    }


}