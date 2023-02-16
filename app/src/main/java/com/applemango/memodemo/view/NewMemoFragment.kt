package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.applemango.memodemo.R
import com.applemango.memodemo.databinding.FragmentNewMemoBinding
import com.applemango.memodemo.viewmodel.NewViewModel

class NewMemoFragment : Fragment() {

    private val bind: FragmentNewMemoBinding by lazy {
        FragmentNewMemoBinding.inflate(LayoutInflater.from(context))
    }

    private lateinit var viewModel: NewViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = requireActivity().run {
            ViewModelProvider(this).get(NewViewModel::class.java)
        }
        bind.lifecycleOwner = this
        bind.viewmodel = viewModel
        initUi()
        return bind.root
    }

    private fun initUi() {
        with(bind) {
            btSave.setOnClickListener {
                viewModel.insert()
                findNavController().navigate(R.id.action_newMemoFragment_to_resultFragment)
                Log.d("SSSSSSSSSSs","done")
            }
        }
    }
}