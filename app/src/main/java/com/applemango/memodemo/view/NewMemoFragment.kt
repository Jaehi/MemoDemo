package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.applemango.memodemo.R
import com.applemango.memodemo.databinding.FragmentNewMemoBinding
import com.applemango.memodemo.viewmodel.Mode
import com.applemango.memodemo.viewmodel.NewViewModel

class NewMemoFragment : Fragment() {

    private val bind: FragmentNewMemoBinding by lazy {
        FragmentNewMemoBinding.inflate(LayoutInflater.from(context))
    }

    private lateinit var viewModel: NewViewModel

    private val args : NewMemoFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = requireActivity().run {
            ViewModelProvider(this).get(NewViewModel::class.java)
        }

        if (args.data == null){
            viewModel.changeMode(Mode.NEW_MEMO)
            viewModel.setData(null)

        }else{
            viewModel.changeMode(Mode.RESULT_MEMO)
            viewModel.setData(args.data!!)
        }

        initView()
        return bind.root
    }

    private fun initView()  {
        with(bind) {
            lifecycleOwner = this@NewMemoFragment
            viewmodel = viewModel
            btSave.setOnClickListener{
                viewModel.modeAction(viewModel.mode.value!!)
            }
        }
    }

}