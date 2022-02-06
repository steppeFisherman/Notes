package com.example.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.domain.models.NoteDomain
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.APP_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : BaseFragment<FragmentNoteBinding>() {

    private val vm by viewModels<NoteFragmentViewModel>()
    private lateinit var mCurrentNote: NoteDomain

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
    }

    private fun initialise() {
        mCurrentNote = arguments?.getSerializable("note") as NoteDomain
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
                    APP_ACTIVITY.navController
                        .navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentNoteBinding.inflate(inflater, container, false)
}