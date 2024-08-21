package com.akansha.digitalsurgery

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun Long.toMinutes(): Int = (this / SECONDS_IN_ONE_MINUTE).toInt()

@RequiresApi(Build.VERSION_CODES.O)
fun String.format(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val date: LocalDateTime = LocalDateTime.parse(this, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return date.format(outputFormatter)
}

private const val SECONDS_IN_ONE_MINUTE = 60