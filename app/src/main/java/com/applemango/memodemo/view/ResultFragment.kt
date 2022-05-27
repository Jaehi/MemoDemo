package com.applemango.memodemo.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.applemango.memodemo.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private val bind : FragmentResultBinding by lazy {
        FragmentResultBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var title = arguments?.getString("title")
        var contents = arguments?.getString("contents")

        if (title != null && contents != null) {
            Log.d("SC","${title}.${contents}")
        }else{
            Log.d("ERROR","ERROR")
        }

        bind.tvTitle.text = title
        bind.tvContents.text = contents

        return bind.root
    }



}