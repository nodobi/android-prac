package com.example.mvp_fragment.view.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp_fragment.R
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentNoteBinding
import com.example.mvp_fragment.view.base.BaseFragment
import com.example.mvp_fragment.view.note.adapter.NoteAdapter
import com.example.mvp_fragment.view.note.contract.NoteContract
import com.example.mvp_fragment.view.note.contract.NotePresenter

class NoteFragment : BaseFragment<FragmentNoteBinding>(), NoteContract.View {
    lateinit var notePresenter: NotePresenter
    lateinit var noteAdapter: NoteAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNoteBinding {
        return FragmentNoteBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        noteAdapter = NoteAdapter(requireContext())
        binding.recyclerviewNoteList.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
        }

        notePresenter = NotePresenter().apply {
            view = this@NoteFragment
            noteAdapterModel = noteAdapter
            noteAdapterView = noteAdapter
            noteRepository = NoteRepository.apply {
                noteLocalDataSource = NoteLocalDataSource.apply {
                    noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                }
            }
        }

        binding.fabNoteAdd.setOnClickListener(notePresenter.onFabClickFunc)

        notePresenter.initFragmentResultListener()

        notePresenter.loadNoteList()
    }

    override fun changeFragment(fragment: Fragment) {
        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction().run {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun registerFragmentResultListener(
        requestKey: String,
        listener: (String, Bundle) -> Unit
    ) {
        setFragmentResultListener(requestKey, listener)
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showAddSucess() {
        showToast("Add Note Success")
    }

    override fun showAddError() {
        showToast("Add Note Error")
    }

    override fun showLoadError() {
        showToast("Load Note Error")
    }
}