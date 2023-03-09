package com.example.mvp_fragment.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.data.source.note.local.NoteDatabase
import com.example.mvp_fragment.data.source.note.local.NoteLocalDataSource
import com.example.mvp_fragment.databinding.FragmentFavoriteBinding
import com.example.mvp_fragment.view.base.BaseFragment
import com.example.mvp_fragment.view.favorite.adapter.FavoriteAdapter
import com.example.mvp_fragment.view.favorite.contract.FavoriteContract
import com.example.mvp_fragment.view.favorite.contract.FavoritePresenter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), FavoriteContract.View {
    private lateinit var mPresenter: FavoritePresenter
    private lateinit var mAdapter: FavoriteAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        mAdapter = FavoriteAdapter(requireContext())
        mPresenter = FavoritePresenter().apply {
            view = this@FavoriteFragment
            favoriteAdapterView = mAdapter
            favoriteAdapterModel = mAdapter
            noteRepository = NoteRepository.apply {
                noteLocalDataSource = NoteLocalDataSource.apply {
                    noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                }
            }
        }

        binding.recyclerviewFavoriteList.apply {
            adapter = this@FavoriteFragment.mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
        }

        mPresenter.loadFavoriteNoteList()

    }
}