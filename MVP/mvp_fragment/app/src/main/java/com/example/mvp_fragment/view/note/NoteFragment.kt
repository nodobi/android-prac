package com.example.mvp_fragment.view.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.databinding.FragmentNoteBinding

class NoteFragment: Fragment() {
    lateinit var binding: FragmentNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentNoteBinding.inflate(layoutInflater, null, false)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}