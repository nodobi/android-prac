package com.example.mvp_fragment.view.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentNoteBinding
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

        notePresenter.loadNoteList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}