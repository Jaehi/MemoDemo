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
            Log.d("dfjkdfjklsdfjkls","${args.data}")
            viewModel.changeMode(Mode.NEW_MEMO)

        }else{
            Log.d("dsfjsdfjkljkldfs","${args.data}")
            viewModel.changeMode(Mode.RESULT_MEMO)
            viewModel.setData(args.data!!)
        }

        Log.d("jkldfjklfsdjkldfsjklfsd","${args.data} ${viewModel.tempData.value} ${viewModel.resultData.value} , ${viewModel.mode.value}")
        initView()
        return bind.root
    }

    private fun initView()  {
        with(bind) {
            btSave.setOnClickListener {
                viewModel.insert()

                Log.d("SSSSSSSSSSs","done")
            }
            lifecycleOwner = this@NewMemoFragment
            viewmodel = viewModel
        }
    }

}