package com.sacrious.nativapps.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sacrious on 1/13/18.
 */
@Entity(tableName = "person")
data class Person(@ColumnInfo(name = "name") val name: String, @ColumnInfo(
    name = "phone") val phone: String, @ColumnInfo(name = "email") val email: String) {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(
      name = "person_id")
  var id: Long = 0
}