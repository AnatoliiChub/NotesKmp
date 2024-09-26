package com.example.composeApp.util

import kotlinx.datetime.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char

class DateFormatter {
    /**
     * Format the given [Instant] to a string. uses the following format:
     * `dd-MM-yyyy HH:mm`
     */
    fun format(date: Instant): String = date.format(DateTimeComponents.Format {
        dayOfMonth()
        char('-')
        monthNumber()
        char('-')
        year()

        char(' ')

        hour()
        char(':')
        minute()

    })

}