package com.sacrious.nativapps.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sacrious on 1/13/18.
 */
@Entity(tableName = "company")
data class Company(@ColumnInfo(name = "name") val name: String, @ColumnInfo(
    name = "address") val address: String, @ColumnInfo(name = "phone") val phone: String) {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(
      name = "company_id")
  var id: Long = 0
}