package com.example.notes.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface ToDispatch {

    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher

    class Base : ToDispatch {
        override fun io() = Dispatchers.IO
        override fun ui() = Dispatchers.Main
    }
}