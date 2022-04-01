package com.example.notes.screens.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.model.NoteApp

class MyDiffUtil(
    private val oldList: List<NoteApp>,
    private val newList: List<NoteApp>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].firebaseId != newList[newItemPosition].firebaseId -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].text != newList[newItemPosition].text -> false
            oldList[oldItemPosition].performDate != newList[newItemPosition].performDate -> false
            oldList[oldItemPosition].performed != newList[newItemPosition].performed -> false
            else -> true
        }
    }
}