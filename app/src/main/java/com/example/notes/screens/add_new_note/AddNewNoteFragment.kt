package com.example.notes.screens.add_new_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.domain.models.NoteDomain
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewNoteFragment : BaseFragment<FragmentAddNewNoteBinding>() {

    private val vm by viewModels<AddNewNoteViewModel>()
    private var dateLongType: Long = 0

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddNewNoteBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.new_note)
        mBinding.inputDateNote.setOnClickListener {
            dateLongType = DatePickDialog.Base().obtainCalendar(it)
        }
        mBinding.btnAddNote.setOnClickListener { addNote() }
    }

    private fun addNote() {
        val name = mBinding.inputNameNote.text.toString()
        val text = mBinding.inputTextNote.text.toString()
        if (name.isEmpty()) {
            showToast(requireActivity(), getString(R.string.toast_enter_name))
        } else {
            vm.insert(NoteDomain(name = name, text = text, performDate = dateLongType)) {
                findNavController()
                    .navigate(R.id.action_addNewNoteFragment_to_mainFragment)
            }
        }
    }
}


