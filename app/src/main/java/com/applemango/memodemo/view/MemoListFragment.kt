package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applemango.memodemo.adapter.MemoAdapter
import com.applemango.memodemo.databinding.FragmentMemoListBinding
import com.applemango.memodemo.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemoListFragment : androidx.fragment.app.Fragment() {

    private val bind: FragmentMemoListBinding by lazy {
        FragmentMemoListBinding.inflate(LayoutInflater.from(context))
    }
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        bind.mRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.refreshData()
        observeData()

        return bind.root
    }

    private fun observeData(){

        Log.d("sdfljjadslhfjkasdhflkas","눗")
        lifecycleScope.launchWhenStarted {
                Log.d("sdfljjadslhfjkasdhflkas","눗눗")
                viewModel.memoList.collect{
                    Log.d("sdfljjadslhfjkasdhflkas","눗뉴순ㅅ")
                    bind.mRecyclerView.adapter = MemoAdapter(
                        it,
                        onClickDelete = { title, content, id ->
                            viewModel.delete(title, content, id)
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