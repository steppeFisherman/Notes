package com.example.notes.screens.add_new_note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.model.NoteApp
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddNewNoteFragment : BaseFragment<FragmentAddNewNoteBinding>() {

    private val vm by viewModels<AddNewNoteViewModel>()
    private var selectedDate: Long = 0

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddNewNoteBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.new_note)
        mBinding.inputDateNote.setOnClickListener {
              DatePick.BaseDatePick().obtainCalendar(it) { date ->
                selectedDate = date
            }
        }
        mBinding.btnAddNote.setOnClickListener { addNote() }
    }

    private fun addNote() {
        val name = mBinding.inputNameNote.text.toString()
        val text = mBinding.inputTextNote.text.toString()
        val date = mBinding.inputDateNote.text.toString()
        when {
            name.isEmpty() -> showToast(requireActivity(), getString(R.string.toast_enter_name))
            date == "Выберите дату" -> showToast(
                requireActivity(),
                getString(R.string.add_date_to_note)
            )
            else -> {
                vm.insert(
                    NoteApp(
                        name = name,
                        text = text,
                        performDate = selectedDate.toString()
                    )
                ) {
                    findNavController()
                        .navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                }
            }
        }
    }
}


