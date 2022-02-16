package com.example.notes.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.domain.models.NoteDomain
import com.example.notes.screens.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val vm by viewModels<MainFragmentViewModel>()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList: Observer<List<NoteDomain>>

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.title)
        setUpAdapter()
        sendDataToAdapter()
        mBinding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    private fun setUpAdapter() {
        mAdapter = MainAdapter(object : MainAdapter.ClickListener {
            override fun click(noteDomain: NoteDomain) {
                val bundle = Bundle()
                bundle.putSerializable("note", noteDomain)
                findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
            }
        })
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
    }

    private fun sendDataToAdapter() {
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        vm.allNotes.observe(viewLifecycleOwner, mObserverList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRecyclerView.adapter = null
    }
}