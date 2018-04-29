package com.sacrious.nativapps.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by sacrious on 1/14/18.
 */
/** Parser of dates **/
class DateFormatterUtils {
  companion object {
    private val dateFormat = "dd/MM/yyyy"
    private val hourFormat = "hh:mm aa"
    private val sdfDate = SimpleDateFormat(dateFormat, Locale.US)
    private val sdfHour = SimpleDateFormat(hourFormat, Locale.US)

    fun convertStringToDate(date: String): Date? {
      return sdfDate.parse(date)
    }

    fun convertDateToString(date: Date): String {
      return sdfDate.format(date)
    }

    fun convertLongToString(date: Long): String {
      return sdfDate.format(date)
    }

    fun convertLongToHour(time: Long): String? {
      return sdfHour.format(time)
    }

    fun convertHourToString(time: Date): String? {
      return sdfHour.format(time)
    }

    fun convertStringToHour(time: String): Date? {
      return sdfHour.parse(time)
    }
  }
}