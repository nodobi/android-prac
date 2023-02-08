package com.example.mvp_fragment.view.favorite.contract

import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.favorite.adapter.FavoriteAdapterContract

interface FavoriteContract {
    interface View {

    }

    interface Presenter {
        var view: View
        var favoriteAdapterView: FavoriteAdapterContract.View
        var favoriteAdapterModel: FavoriteAdapterContract.Model
        var noteRepository: NoteRepository

        fun loadFavoriteNoteList()


    }
}