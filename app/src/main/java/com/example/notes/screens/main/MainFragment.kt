package com.example.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.domain.models.Result
import com.example.notes.model.NoteApp
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), MainAdapter.ClickListener {

    private val vm by viewModels<MainFragmentViewModel>()
    private lateinit var mAdapter: MainAdapter

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().title = getString(R.string.title)
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
        val searchView = menuItem.actionView as SearchView
        SearchViewListener.BaseSearchView().search(searchView, mAdapter)
        super.onPrepareOptionsMenu(menu)
    }

    private fun sendDataToAdapter() {
        mAdapter = MainAdapter(this)
        mBinding.recyclerView.adapter = mAdapter
        vm.allNotes.observe(viewLifecycleOwner, {
            val list = it.asReversed()
            mAdapter.setList(list)
        })
    }

    override fun click(noteApp: NoteApp) {
        val bundle = Bundle()
        bundle.putParcelable("note", noteApp)
        findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }
}