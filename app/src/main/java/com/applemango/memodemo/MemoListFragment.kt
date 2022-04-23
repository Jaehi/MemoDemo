package com.applemango.memodemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applemango.memodemo.databinding.FragmentMemoListBinding

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
                        val bundle = bundleOf("position" to position)
                        val resultFragment = ResultFragment()
                        resultFragment.arguments = bundle
                        findNavController().navigate(R.id.action_memoListFragment_to_resultFragment)
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