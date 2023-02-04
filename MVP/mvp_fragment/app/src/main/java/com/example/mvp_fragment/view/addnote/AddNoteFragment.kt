package com.example.mvp_fragment.view.addnote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.mvp_fragment.R
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentAddnoteBinding
import com.example.mvp_fragment.view.addnote.contract.AddNoteContract
import com.example.mvp_fragment.view.addnote.contract.AddNotePresenter

class AddNoteFragment(val noteId: String?) : Fragment(), AddNoteContract.View {
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
            noteId = this@AddNoteFragment.noteId
            noteRepository = NoteRepository.apply {
                noteLocalDataSource = NoteLocalDataSource.apply {
                    noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                }
            }
        }

        binding.toolbarAddnote.apply {
            setNavigationOnClickListener {
                addNotePresenter.onNavClickFunc?.invoke(Unit)
            }
            setOnMenuItemClickListener {
                addNotePresenter.onToolbarItemClickFunc?.invoke(
                    it,
                    binding.edittextAddnoteTitle.text.toString(),
                    binding.edittextAddnoteDetail.text.toString()
                )
                false
            }
            if(addNotePresenter.isEditNote()) {
                inflateMenu(R.menu.menu_editnote_toolbar)
            } else {
                inflateMenu(R.menu.menu_addnote_toolbar)
            }
        }

        if(addNotePresenter.isEditNote()) {
            addNotePresenter.updateNote()
            binding.toolbarAddnote.title = "EditNoteFragment"
        }
    }

    override fun showLoadError() {
        Toast.makeText(context, "Note Load Error", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun popBackFragment(isAdded: Boolean) {
        setFragmentResult(REQUEST_ADD_NOTE, Bundle().apply {
            if (isAdded) putInt(EXTRA_RESULT, RESULT_OK)
            else putInt(EXTRA_RESULT, RESULT_CANDELED)
        })
        parentFragmentManager.popBackStack()
    }

    override fun setTitle(title: String) {
        binding.edittextAddnoteTitle.setText(title)
    }

    override fun setDetail(detail: String) {
        binding.edittextAddnoteDetail.setText(detail)
    }
}