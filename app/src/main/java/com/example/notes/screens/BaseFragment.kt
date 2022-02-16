package com.example.notes.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.notes.domain.models.NoteDomain

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var binding: B? = null
    protected val mBinding get() = checkNotNull(binding)

    protected abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}