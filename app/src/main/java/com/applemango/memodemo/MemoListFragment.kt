package com.applemango.memodemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applemango.memodemo.databinding.FragmentMemoListBinding
import java.lang.NullPointerException

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
            bind.mRecyclerView.adapter = MemoAdapter(it, onClickDelete = { title, content, id ->
                Log.d("delete", "onCreateView: $title , $content $id")
                viewModel.delete(title, content, id)
            },
                    onClick = { position ->
                        //safeargs
                        val bundle = Bundle()
                        bundle.putString("title", viewModel.memoList.value?.get(position)?.title)
                        bundle.putString("contents",viewModel.memoList.value?.get(position)?.content)

                        findNavController().navigate(R.id.action_memoListFragment_to_resultFragment, bundle)
                    }
            )
        })

        initUI()
        return bind.root
    }

    private fun initUI() {
        bind.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}