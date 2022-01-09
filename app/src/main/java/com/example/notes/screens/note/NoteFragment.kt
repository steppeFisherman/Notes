package com.example.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.domain.models.NoteDomain
import com.example.notes.utils.APP_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var binding: FragmentNoteBinding? = null
    private val mBinding get() = binding!!
    private val vm: NoteFragmentViewModel by viewModels()
    private lateinit var mCurrentNote: NoteDomain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as NoteDomain
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
    }

    private fun initialise() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}