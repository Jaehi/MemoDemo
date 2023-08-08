package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.applemango.memodemo.R
import com.applemango.memodemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _bind: FragmentMainBinding? = null
    private val bind get() = _bind!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _bind = FragmentMainBinding.inflate(inflater, container, false)

        bind.lifecycleOwner = this
        bind.fragment = this

        return bind.root

    }

    fun gotoNewMemo() {
        val action = MainFragmentDirections.actionMainFragmentToNewMemoFragment(null)
        findNavController().navigate(action)
    }

    fun gotoListMemo() {
        findNavController().navigate(R.id.action_mainFragment_to_memoListFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}