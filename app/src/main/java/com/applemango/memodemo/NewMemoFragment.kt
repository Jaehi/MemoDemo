package com.applemango.memodemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.applemango.memodemo.databinding.FragmentNewMemoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.EnumSet.of
import java.util.Optional.of
import java.util.Set.of


@SuppressLint("UseRequireInsteadOfGet")
class NewMemoFragment : Fragment() {

    private val bind : FragmentNewMemoBinding by lazy {
        FragmentNewMemoBinding.inflate(LayoutInflater.from(context))

    }

//    private val viewmodel : MainViewModel by lazy {
//        ViewModelProvider(this,MainViewModel.Factory(activity!!.application)).get(MainViewModel::class.java)
//    }
    private lateinit var viewmodel : MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewmodel = requireActivity().run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }

        bind.lifecycleOwner = this
        bind.viewmodel = viewmodel


        viewmodel.getData().observe(viewLifecycleOwner, object : Observer<List<MemoData>>{

            override fun onChanged(t: List<MemoData>?) {

                Log.d("9999999999999","$t")
            }

        })

        bind.btSave.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewmodel.insert(bind.etTitle.text.toString(),bind.etContents.text.toString())
                }
            }

        })




        return bind.root
    }

}