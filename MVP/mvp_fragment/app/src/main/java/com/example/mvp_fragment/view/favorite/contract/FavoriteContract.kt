package com.example.mvp_fragment.view.favorite.contract

import com.example.mvp_fragment.data.source.note.NoteRepository
import com.example.mvp_fragment.view.base.BaseContract
import com.example.mvp_fragment.view.favorite.adapter.FavoriteAdapterContract

interface FavoriteContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        var favoriteAdapterView: FavoriteAdapterContract.View
        var favoriteAdapterModel: FavoriteAdapterContract.Model
        var noteRepository: NoteRepository

        fun loadFavoriteNoteList()


    }
}