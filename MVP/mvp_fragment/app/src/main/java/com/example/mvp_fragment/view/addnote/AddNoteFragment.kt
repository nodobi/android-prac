package com.example.mvp_fragment.view.addnote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.mvp_fragment.R
import com.example.mvp_fragment.data.NoteItem
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentAddnoteBinding
import com.example.mvp_fragment.view.addnote.contract.AddNoteContract
import com.example.mvp_fragment.view.addnote.contract.AddNotePresenter

class AddNoteFragment : Fragment(), AddNoteContract.View {
    companion object {
        val RESULT_OK = 1
        val RESULT_CANDELED = 0
        val REQUEST_ADD_NOTE = "REQUEST_ADD_NOTE"
        val EXTRA_RESULT = "EXTRA_RESULT"
    }

    private lateinit var binding: FragmentAddnoteBinding
    private lateinit var addNotePresenter: AddNotePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddnoteBinding.inflate(layoutInflater, null, false)

        addNotePresenter = AddNotePresenter().apply {
            view = this@AddNoteFragment
            noteRepository = NoteRepository.apply {
                noteLocalDataSource = NoteLocalDataSource.apply {
                    noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                }
            }
        }

        binding.toolbarAddnote.apply {
            setNavigationOnClickListener {
                addNotePresenter.onNavClickFunc
            }
            setOnMenuItemClickListener {
                addNotePresenter.onToolbarItemClickFunc?.invoke(
                    NoteItem(
                        binding.edittextAddnoteTitle.text.toString(),
                        binding.edittextAddnoteDetail.text.toString()
                    )
                )
                false
            }
            inflateMenu(R.menu.menu_addnote_toolbar)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun popBackFragment() {
        setFragmentResult(REQUEST_ADD_NOTE, Bundle().apply {
            putInt(EXTRA_RESULT, RESULT_OK)
        })
        parentFragmentManager.popBackStack()
    }

}