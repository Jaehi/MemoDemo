package com.applemango.memodemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applemango.memodemo.databinding.MemoItemBinding

class MemoAdapter(private val data: List<MemoData>, val onClickDelete: (title:String, content: String, id: Int) -> Unit,
                  val onClick : (position : Int) -> Unit
) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    inner class ViewHolder(private val bind: MemoItemBinding) : RecyclerView.ViewHolder(bind.root) {

        fun bindmemo(memo: MemoData) {
            bind.itemTitle.text = memo.title
            bind.btDelete.setOnClickListener {
                onClickDelete(memo.title, memo.content, memo.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindmemo(data[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}