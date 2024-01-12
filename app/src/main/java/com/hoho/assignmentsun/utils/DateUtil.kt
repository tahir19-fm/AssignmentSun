package com.hoho.assignmentsun.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {
    fun getCurrentDateFormatted(): String {
        return try {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            currentDate.format(formatter)
        }catch (e:Exception){
            "-"
        }

    }
     fun convertIsoToTime(isoTimestamp: String): String {
         return try {
             val formatter = DateTimeFormatter.ofPattern("HH:mm")
             val dateTime = LocalDateTime.parse(isoTimestamp, DateTimeFormatter.ISO_DATE_TIME)
             dateTime.format(formatter)
         }catch (e:Exception){
             "-"
         }

    }
}