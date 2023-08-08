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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        observeData()

        _bind = FragmentMemoListBinding.inflate(inflater,container,false)

        bind.mRecyclerView.layoutManager = LinearLayoutManager(context)

        return bind.root
    }

    private fun refreshMemoData(){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.refreshData()
        }
    }


    private fun observeData(){

        CoroutineScope(Dispatchers.Main).launch {

            viewModel.refreshData()

            viewModel.memoList.collect{
                Log.d("내 눈물은 먼지 사랑이 뭔지","$it")
                bind.mRecyclerView.adapter = MemoAdapter(
                    it,
                    onClickDelete = { title, content, id ->
                        context?.let { context -> MemoDialog(context , deleteMemo = {
                            Log.d("context aru?","$context")
                            viewModel.delete(title, content, id)
                        }).show() }
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
}