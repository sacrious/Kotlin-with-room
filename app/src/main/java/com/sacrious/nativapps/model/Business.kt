package com.sacrious.nativapps.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.Date


/**
 * Created by sacrious on 1/13/18.
 */
@Entity(tableName = "business",
    foreignKeys = [(ForeignKey(entity = Person::class, parentColumns = arrayOf("person_id"),
        childColumns = arrayOf("person_id"), onDelete = ForeignKey.RESTRICT)), (ForeignKey(
        entity = Company::class, parentColumns = arrayOf("company_id"),
        childColumns = arrayOf("company_id"), onDelete = ForeignKey.RESTRICT))])
data class Business(@ColumnInfo(name = "title") val title: String, @ColumnInfo(
    name = "description") val description: String, @ColumnInfo(
    name = "value") val value: Double, @ColumnInfo(
    name = "date") val date: Date?, @ColumnInfo(
    name = "status") val status: Boolean, @ColumnInfo(
    name = "person_id") val personId: Long, @ColumnInfo(name = "company_id") val companyId: Long) {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(
      name = "business_id")
  var id: Long = 0
}