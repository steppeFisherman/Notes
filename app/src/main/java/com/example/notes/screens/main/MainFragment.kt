package com.example.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.model.NoteApp
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.hideKeyboard
import com.example.notes.utils.showSnackLong
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

        vm.error.observe(viewLifecycleOwner, {
            when (it.ordinal) {
                0 -> view?.showSnackLong(R.string.no_connection_message)
                1 -> view?.showSnackLong(R.string.service_unavailable_message)
                2 -> view?.showSnackLong(R.string.null_pointer_exception)
                3 -> view?.showSnackLong(R.string.something_went_wrong)
            }
        })
    }

    override fun click(noteApp: NoteApp) {
        val bundle = Bundle()
        bundle.putParcelable("note", noteApp)
        findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }
}