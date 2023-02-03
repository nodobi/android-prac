package com.example.mvp_fragment.data.source.note

open class LocalDataException(msg: String): Exception(msg)

class LocalDataNotFoundException: LocalDataException("LocalDataNotFounds")