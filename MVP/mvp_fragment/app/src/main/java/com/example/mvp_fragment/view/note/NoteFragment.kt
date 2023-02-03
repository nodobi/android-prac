package com.example.mvp_fragment.view.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
import com.example.mvp_fragment.view.addnote.AddNoteFragment
import com.example.mvp_fragment.view.note.adapter.NoteAdapter
import com.example.mvp_fragment.view.note.contract.NoteContract
import com.example.mvp_fragment.view.note.contract.NotePresenter

class NoteFragment : Fragment(), NoteContract.View {

    lateinit var binding: FragmentNoteBinding
    lateinit var notePresenter: NotePresenter
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNoteBinding.inflate(layoutInflater, null, false)

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

        setFragmentResultListener(AddNoteFragment.REQUEST_ADD_NOTE) { requestKey, bundle ->
            notePresenter.fragmentResultFunc?.invoke(requestKey, bundle)
        }

        notePresenter.loadNoteList()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun changeFragment(fragment: Fragment) {
        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction().run {
            replace(R.id.fragment_container, AddNoteFragment())
            addToBackStack(null)
            commit()
        }
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