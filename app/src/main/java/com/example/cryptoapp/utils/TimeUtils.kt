package com.example.cryptoapp.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun convertTimeStampToDate(timeStamp: Long): String {
    val stamp = Timestamp(timeStamp * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
 //   sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

fun convertTimeStampToTime(timeStamp: Long): String {
    val stamp = Timestamp(timeStamp * 1000)
    val date = Date(stamp.time)
    val pattern = "dd.MM.yyyy"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
   // sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}