package com.badabusiness.demo.utils

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    @JvmStatic
    fun getDate(date: Date): String {//2006-11-17
        return try {
            SimpleDateFormat("dd/mm/yyyy").format(date)
        } catch (e: Exception) {
            "N/A"
        }
    }

}