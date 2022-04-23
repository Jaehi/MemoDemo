package com.applemango.memodemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applemango.memodemo.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private val bind : FragmentResultBinding by lazy {
        FragmentResultBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

}