package com.example.notes.screens.main

import androidx.appcompat.widget.SearchView

interface SearchViewListener {

    fun search(searchView: SearchView, adapter: MainAdapter)

    class BaseSearchView : SearchViewListener {

        override fun search(searchView: SearchView, adapter: MainAdapter) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    adapter.filter.filter(newText)
                    return true
                }
            })
        }
    }
}

