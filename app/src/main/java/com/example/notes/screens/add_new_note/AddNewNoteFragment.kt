package com.example.notes.screens.add_new_note

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.model.NoteApp
import com.example.notes.screens.BaseFragment
import com.example.notes.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.*

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
            BaseDatePick().obtainCalendar(it)
        }
        mBinding.btnAddNote.setOnClickListener { addNote() }
    }

    private fun addNote() {
        val name = mBinding.inputNameNote.text.toString()
        val text = mBinding.inputTextNote.text.toString()
        if (name.isEmpty()) {
            showToast(requireActivity(), getString(R.string.toast_enter_name))
        } else {
            vm.insert(
                NoteApp(
                    name = name,
                    text = text,
                    performDate = dateLongType.toString()
                )
            ) {
                findNavController()
                    .navigate(R.id.action_addNewNoteFragment_to_mainFragment)
            }
        }
    }

    inner class BaseDatePick {
        fun obtainCalendar(view: View) {
            val currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                view.context,
                { _, year, month, day ->
                    val pickedDateTime = Calendar.getInstance()
                    pickedDateTime.set(Calendar.YEAR, year)
                    pickedDateTime.set(Calendar.MONTH, month)
                    pickedDateTime.set(Calendar.DAY_OF_MONTH, day)

                    dateLongType = pickedDateTime.time.time
                    val text = DateFormat.getDateInstance()
                        .format(pickedDateTime.time)
                    (view as Button).text = text
                },
                startYear, startMonth, startDay,
            ).show()
        }
    }
}


