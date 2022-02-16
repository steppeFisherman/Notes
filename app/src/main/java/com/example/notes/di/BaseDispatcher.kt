package com.example.notes.di

import kotlinx.coroutines.Dispatchers

class BaseDispatcher {
    fun io() = Dispatchers.IO
    fun ui() = Dispatchers.Main
}

