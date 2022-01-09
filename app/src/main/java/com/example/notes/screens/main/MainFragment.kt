package com.example.notes.screens.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.domain.models.NoteDomain
import com.example.notes.utils.APP_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!
    private val vm: MainFragmentViewModel by viewModels()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList: Observer<List<NoteDomain>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        hideKeyboard(view)
    }

    private fun initialise() {
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        vm.allNotes.observe(viewLifecycleOwner, mObserverList)
        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.navController
                .navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        mRecyclerView.adapter = null
    }

    companion object {
        fun click(note: NoteDomain) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController
                .navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }
}