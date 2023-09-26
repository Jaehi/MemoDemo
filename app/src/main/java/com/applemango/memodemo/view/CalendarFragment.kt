package com.applemango.memodemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applemango.memodemo.R
import com.applemango.memodemo.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private var _bind : FragmentCalendarBinding? = null

    private val bind get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _bind = FragmentCalendarBinding.inflate(inflater,container,false)

        return bind.root
    }

}