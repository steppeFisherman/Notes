package com.example.notes.screens.add_new_note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.domain.models.NoteDomain
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewNoteFragment : BaseFragment<FragmentAddNewNoteBinding>() {

    private val vm by viewModels<AddNewNoteViewModel>()

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
            vm.insert(NoteDomain(name = name, text = text)) {
                APP_ACTIVITY.navController
                    .navigate(R.id.action_addNewNoteFragment_to_mainFragment)
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddNewNoteBinding {
        Log.d("AAA", "AddNewNoteFragment initBinding")
        return FragmentAddNewNoteBinding.inflate(inflater, container, false)
    }
}


