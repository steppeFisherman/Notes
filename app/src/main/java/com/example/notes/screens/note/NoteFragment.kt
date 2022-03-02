package com.example.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.model.NoteApp
import com.example.notes.screens.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : BaseFragment<FragmentNoteBinding>() {

    private val vm by viewModels<NoteFragmentViewModel>()
    private lateinit var mCurrentNote: NoteApp

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentNoteBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.note)
        initialise()
    }

    private fun initialise() {
        mCurrentNote = arguments?.getParcelable("note") ?: NoteApp()
        setHasOptionsMenu(true)
        mBinding.noteText.text = mCurrentNote.text
        mBinding.noteName.text = mCurrentNote.name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                vm.delete(mCurrentNote) {
                    findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}