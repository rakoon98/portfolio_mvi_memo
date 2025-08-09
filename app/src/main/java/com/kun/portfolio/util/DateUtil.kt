package com.kun.portfolio.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtil {

    private val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

    // timeStamp
    fun Long.toStringFormat(): String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(this),
            ZoneId.systemDefault()
        )

        return localDateTime.format(formatter)
    }

}