package com.example.notes.screens.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.model.NoteApp
import java.util.*

class MainAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<MainAdapter.MainHolder>(), Filterable {

    private var mListNotes = emptyList<NoteApp>()
    private var mListNotesFiltered = emptyList<NoteApp>()

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
            clickListener.click(mListNotesFiltered[holder.adapterPosition])
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
        holder.binding.itemNoteName.text = mListNotesFiltered[position].name
        holder.binding.itemNoteText.text = mListNotesFiltered[position].text
        holder.binding.itemNoteDate.text = mListNotesFiltered[position].performDate
    }

    override fun getItemCount(): Int = mListNotesFiltered.size

    class MainHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<NoteApp>) {
        val diffUtil =MyDiffUtil(mListNotesFiltered, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        mListNotes = newList
        mListNotesFiltered = newList
        diffResult.dispatchUpdatesTo(this)
    }

    interface ClickListener {
        fun click(noteApp: NoteApp)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val key = charSequence.toString().lowercase(Locale.ROOT).trim()
                mListNotesFiltered = if (key.isEmpty()) mListNotes
                else {
                    val newList = mutableListOf<NoteApp>()
                    for (note in mListNotes) {
                        val name = note.name.lowercase(Locale.ROOT).trim()
                        if (name.contains(key)) newList.add(note)
                    }
                    newList
                }
                val filterResults = FilterResults()
                filterResults.values = mListNotesFiltered
                filterResults.count = mListNotesFiltered.size
                return filterResults
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                mListNotesFiltered = filterResults?.values as List<NoteApp>
                notifyDataSetChanged()
            }
        }
    }
}


