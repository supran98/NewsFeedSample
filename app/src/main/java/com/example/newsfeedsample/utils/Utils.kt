package com.example.newsfeedsample.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun simplifyIsoDate(isoDate: String?): String {
    if (isoDate == null) {
        return ""
    }
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
    return LocalDate.parse(isoDate, formatter).toString()
}