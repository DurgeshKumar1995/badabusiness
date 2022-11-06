package com.badabusiness.demo.ui.note_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.badabusiness.demo.databinding.ItemNoteBinding
import com.badabusiness.demo.model.Note

class NoteAdapter(private val clicked: (Note?) -> Unit): PagingDataAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem == newItem
        }
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Note?) {
            binding.note = data

            binding.let {
                it.root.setOnClickListener {
                    clicked.invoke(data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
