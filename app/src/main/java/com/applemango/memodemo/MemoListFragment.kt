package com.applemango.memodemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applemango.memodemo.databinding.FragmentMemoListBinding

class MemoListFragment : Fragment() {

    private val bind : FragmentMemoListBinding by lazy {
        FragmentMemoListBinding.inflate(LayoutInflater.from(context))
    }
    private val viewModel : ListViewModel by viewModels()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        bind.mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.memoList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            bind.mRecyclerView.adapter = MemoAdapter(it) { title, content ,id->
                Log.d("delete", "onCreateView: $title , $content $id")
                viewModel.delete(title, content, id)
            }
        })


        initUI()
        return bind.root
    }

    private fun initUI(){
        bind.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onClick(){

    }



}