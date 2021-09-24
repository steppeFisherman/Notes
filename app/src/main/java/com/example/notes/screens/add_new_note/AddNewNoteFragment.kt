package com.example.notes.screens.add_new_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.model.AppNote
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.showToast

class AddNewNoteFragment : Fragment() {

    private var binding: FragmentAddNewNoteBinding? = null
    private val mBinding get() = binding!!
    private val viewModel by viewModels<AddNewNoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewNoteBinding
            .inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnAddNote.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        val name = mBinding.inputNameNote.text.toString()
        val text = mBinding.inputTextNote.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.toast_enter_name))
        } else {
            viewModel.insert(AppNote(name = name, text = text)) {
                APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}