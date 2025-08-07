package com.austral.hr_galicia.util

object DateParser {
    fun parseDate(date: String) : String {
        val parsedDate = date.substring(0, 10)
        val split = parsedDate.split("-")
        return "${split[2]}/${split[1]}/${split[0]}"
    }
}