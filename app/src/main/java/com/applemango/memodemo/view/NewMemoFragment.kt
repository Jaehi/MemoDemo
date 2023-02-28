package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.applemango.memodemo.R
import com.applemango.memodemo.databinding.FragmentNewMemoBinding
import com.applemango.memodemo.viewmodel.Mode
import com.applemango.memodemo.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewMemoFragment : Fragment() {

    private val bind: FragmentNewMemoBinding by lazy {
        FragmentNewMemoBinding.inflate(LayoutInflater.from(context))
    }

    private val viewModel : NewViewModel by viewModels()

    private val args : NewMemoFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

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
                when(viewModel.mode.value){
                    Mode.NEW_MEMO -> {
                        Toast.makeText(requireContext(),"저장 완료", Toast.LENGTH_SHORT).show()
                    }
                    Mode.EDIT_MEMO -> {
                        Toast.makeText(requireContext(),"수정 완료",Toast.LENGTH_SHORT).show()
                    }
                    else -> {

                    }
                }
                viewModel.modeAction(viewModel.mode.value!!)
            }
        }
    }

}