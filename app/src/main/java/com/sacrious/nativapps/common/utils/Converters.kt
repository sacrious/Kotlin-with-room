package com.sacrious.nativapps.common.utils

import android.arch.persistence.room.TypeConverter
import java.util.Date

/**
 * Created by sacrious on 1/13/18.
 */
/** Needed for storing Date types in room database **/
object Converters {
  @TypeConverter
  @JvmStatic
  fun fromTimestamp(value: Long?): Date? {
    return if (value == null) null else Date(value)
  }

  @TypeConverter
  @JvmStatic
  fun dateToTimestamp(date: Date?): Long? {
    return date?.time
  }
}