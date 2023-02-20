package com.applemango.memodemo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applemango.memodemo.adapter.MemoAdapter
import com.applemango.memodemo.databinding.FragmentMemoListBinding
import com.applemango.memodemo.viewmodel.ListViewModel

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

        viewModel.memoList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            bind.mRecyclerView.adapter = MemoAdapter(it,
                onClickDelete = { title, content, id ->
                Log.d("delete", "onCreateView: $title , $content $id")
                viewModel.delete(title, content, id)
            },
                    onClick = { position ->
                        //safeargs

                        val tempData = viewModel.memoList.value?.get(position)

                        val action = MemoListFragmentDirections.actionMemoListFragmentToNewMemoFragment(tempData)

                        findNavController().navigate(action)



                    }
            )
        })

        return bind.root
    }


}