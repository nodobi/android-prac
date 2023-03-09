package com.example.mvp_fragment.view.addnote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.example.mvp_fragment.R
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentAddnoteBinding
import com.example.mvp_fragment.view.addnote.contract.AddNoteContract
import com.example.mvp_fragment.view.addnote.contract.AddNotePresenter
import com.example.mvp_fragment.view.base.BaseFragment

class AddNoteFragment(private val noteId: String?) : BaseFragment<FragmentAddnoteBinding>(), AddNoteContract.View {
    companion object {
        const val RESULT_OK = 1
        const val RESULT_CANCELED = 0
        const val REQUEST_ADD_NOTE = "REQUEST_ADD_NOTE"
        const val EXTRA_RESULT = "EXTRA_RESULT"
    }

    private lateinit var mPresenter: AddNotePresenter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddnoteBinding {
        return FragmentAddnoteBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        mPresenter = AddNotePresenter().apply {
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
                mPresenter.onNavClickFunc?.invoke(Unit)
            }
            setOnMenuItemClickListener {
                mPresenter.onToolbarItemClickFunc?.invoke(
                    it,
                    binding.edittextAddnoteTitle.text.toString(),
                    binding.edittextAddnoteDetail.text.toString()
                )
                false
            }
            if (mPresenter.isEditNote()) {
                inflateMenu(R.menu.menu_editnote_toolbar)
            } else {
                inflateMenu(R.menu.menu_addnote_toolbar)
            }
        }

        if (mPresenter.isEditNote()) {
            mPresenter.updateNote()
            binding.toolbarAddnote.title = "EditNoteFragment"
        }
    }

    override fun showLoadError() {
        Toast.makeText(context, "Note Load Error", Toast.LENGTH_SHORT).show()
    }

    override fun popBackFragment(isAdded: Boolean) {
        setFragmentResult(REQUEST_ADD_NOTE, Bundle().apply {
            if (isAdded) putInt(EXTRA_RESULT, RESULT_OK)
            else putInt(EXTRA_RESULT, RESULT_CANCELED)
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