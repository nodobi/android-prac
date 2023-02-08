package com.example.mvp_fragment.view.favorite

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
import com.example.mvp_fragment.databinding.FragmentFavoriteBinding
import com.example.mvp_fragment.view.favorite.adapter.FavoriteAdapter
import com.example.mvp_fragment.view.favorite.contract.FavoriteContract
import com.example.mvp_fragment.view.favorite.contract.FavoritePresenter

class FavoriteFragment : Fragment(), FavoriteContract.View {
    lateinit var binding: FragmentFavoriteBinding
    lateinit var presenter: FavoritePresenter
    lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, null, false)

        adapter = FavoriteAdapter(requireContext())
        presenter = FavoritePresenter().apply {
            view = this@FavoriteFragment
            favoriteAdapterView = adapter
            favoriteAdapterModel = adapter
            noteRepository = NoteRepository.apply {
                noteLocalDataSource = NoteLocalDataSource.apply {
                    noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                }
            }
        }

        binding.recyclerviewFavoriteList.apply {
            adapter = this@FavoriteFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
        }

        presenter.loadFavoriteNoteList()

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