package com.applemango.memodemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applemango.memodemo.data.MemoData
import com.applemango.memodemo.databinding.MemoItemBinding

class MemoAdapter(
    private val data: List<MemoData>,
    val onClickDelete: (title: String, content: String, id: Int,date : String) -> Unit,
    val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    inner class ViewHolder(private val bind: MemoItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bindMemo(memo: MemoData) {
            bind.itemTitle.text = memo.title
            bind.btDelete.setOnClickListener {
                onClickDelete(memo.title, memo.content, memo.id,memo.date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MemoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMemo(data[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}