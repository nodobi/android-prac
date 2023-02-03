package com.example.mvp_fragment.data.source.note

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error() : Result<Nothing>()
}