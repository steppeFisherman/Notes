package com.example.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.model.NoteApp
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), MainAdapter.ClickListener {

    private val vm by viewModels<MainFragmentViewModel>()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList: Observer<List<NoteApp>>
    private lateinit var mSearchView: SearchView


    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().title = getString(R.string.title)
        setUpAdapter()
        sendDataToAdapter()
        mBinding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
        hideKeyboard(requireActivity(), view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.fragmentGuard_search)
        mSearchView = menuItem.actionView as SearchView
        SearchViewListener.BaseSearchView().search(mSearchView, mAdapter)
        super.onPrepareOptionsMenu(menu)
    }

    private fun setUpAdapter() {
        mAdapter = MainAdapter(this)
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

    override fun click(noteApp: NoteApp) {
        val bundle = Bundle()
        bundle.putParcelable("note", noteApp)
        findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRecyclerView.adapter = null
    }
}