package com.example.notes.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.domain.models.NoteDomain

class MainAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var mListNotes = emptyList<NoteDomain>()

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
            clickListener.click(mListNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = NoteItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mListNotes[position].name
        holder.textNote.text = mListNotes[position].text
    }

    override fun getItemCount(): Int = mListNotes.size

    class MainHolder(view: NoteItemBinding) : RecyclerView.ViewHolder(view.root) {
        val nameNote = view.itemNoteName
        val textNote = view.itemNoteText
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteDomain>) {
        mListNotes = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun click(noteDomain: NoteDomain)
    }
}


