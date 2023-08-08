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
import com.applemango.memodemo.dialog.MemoToast
import com.applemango.memodemo.viewmodel.Mode
import com.applemango.memodemo.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewMemoFragment : Fragment() {

    private var _bind: FragmentNewMemoBinding? = null

    private val bind get() = _bind!!

    private val viewModel: NewViewModel by viewModels()

    private val args: NewMemoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _bind = FragmentNewMemoBinding.inflate(inflater, container, false)

        if (args.data == null) {
            viewModel.changeMode(Mode.NEW_MEMO)
            viewModel.setData(null)

        } else {
            viewModel.changeMode(Mode.RESULT_MEMO)
            viewModel.setData(args.data!!)
        }

        initView()
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    private fun initView() {
        with(bind) {
            lifecycleOwner = this@NewMemoFragment
            viewmodel = viewModel
            btSave.setOnClickListener {
                when (viewModel.mode.value) {
                    Mode.NEW_MEMO -> {
                        MemoToast.makeToast(requireContext(), "수정 완료", true).show()
                    }

                    Mode.EDIT_MEMO -> {
                        MemoToast.makeToast(requireContext(), "수정 완료", false).show()
                    }

                    else -> {

                    }
                }
                viewModel.modeAction(viewModel.mode.value!!)
            }
        }
    }

}