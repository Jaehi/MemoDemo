package com.applemango.memodemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.applemango.memodemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val bind : FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onClick()
        return bind.root

    }

    private fun onClick(){
        bind.btWrite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_newMemoFragment)
        }

        bind.btGoList.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_memoListFragment)
        }

    }
}