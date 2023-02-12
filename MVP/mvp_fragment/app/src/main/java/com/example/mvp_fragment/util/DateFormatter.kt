package com.example.mvp_fragment.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val ISO_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE

    fun localDateToString(localDate: LocalDate): String {
        return localDate.format(ISO_FORMAT).toString()
    }

    fun stringToLocalDate(string: String): LocalDate {
        return LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}