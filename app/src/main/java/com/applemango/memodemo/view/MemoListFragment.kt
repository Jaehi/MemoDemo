package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applemango.memodemo.adapter.MemoAdapter
import com.applemango.memodemo.databinding.FragmentMemoListBinding
import com.applemango.memodemo.dialog.MemoDialog
import com.applemango.memodemo.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemoListFragment : androidx.fragment.app.Fragment() {

    private var _bind: FragmentMemoListBinding? = null
    private val bind get() = _bind!!

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        observeData()

        _bind = FragmentMemoListBinding.inflate(inflater, container, false)

        bind.mRecyclerView.layoutManager = LinearLayoutManager(context)

        return bind.root
    }

    private fun observeData() {

        CoroutineScope(Dispatchers.Main).launch {

            viewModel.refreshData()

            viewModel.memoList.collect {
                bind.mRecyclerView.adapter = MemoAdapter(
                    it, onClickDelete = { title, content, id, date -> context?.let { context ->
                            MemoDialog(context, deleteMemo = {
                                viewModel.delete(title, content ,date,id)
                            }).show()
                        }
                    }
                ) { position ->
                    //safeargs
                    val tempData = it[position]

                    val action =
                        MemoListFragmentDirections.actionMemoListFragmentToNewMemoFragment(
                            tempData
                        )

                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}