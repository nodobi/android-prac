package com.example.mvp_fragment.view.favorite.contract

import com.example.mvp_fragment.data.source.note.Result
import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.favorite.adapter.FavoriteAdapterContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritePresenter : FavoriteContract.Presenter {
    override lateinit var view: FavoriteContract.View
    override lateinit var favoriteAdapterView: FavoriteAdapterContract.View
    override lateinit var favoriteAdapterModel: FavoriteAdapterContract.Model
    override lateinit var noteRepository: NoteRepository

    override fun loadFavoriteNoteList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = noteRepository.getFavoriteNotes()
            when (result) {
                is Result.Success -> {
                    favoriteAdapterModel.updateNoteList(result.data)
                    favoriteAdapterView.notifyAdapter()
                }
                is Result.Error -> {

                }
            }
        }
    }


}