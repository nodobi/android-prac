package com.example.mvp_fragment.view.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.databinding.FragmentAddnoteBinding
import com.example.mvp_fragment.view.addnote.contract.AddNoteContract
import com.example.mvp_fragment.view.addnote.contract.AddNotePresenter

class AddNoteFragment : Fragment(), AddNoteContract.View {

    private lateinit var binding: FragmentAddnoteBinding
    private lateinit var addNotePresenter: AddNotePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddnoteBinding.inflate(layoutInflater, null, false)

        addNotePresenter = AddNotePresenter().apply {
            view = this@AddNoteFragment
        }

        binding.toolbarAddnote.setNavigationOnClickListener(addNotePresenter.onNavClickFunc)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun popBackFragment() {
        parentFragmentManager.popBackStack()
    }
}