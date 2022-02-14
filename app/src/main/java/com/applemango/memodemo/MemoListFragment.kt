package com.applemango.memodemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applemango.memodemo.databinding.FragmentMemoListBinding

class MemoListFragment : Fragment() {

    private val bind : FragmentMemoListBinding by lazy {
        FragmentMemoListBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return bind.root
    }

}