package com.sacrious.nativapps.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.Date

/**
 * Created by sacrious on 1/14/18.
 */
@Entity(tableName = "activity",
    foreignKeys = [(ForeignKey(entity = Person::class, parentColumns = arrayOf("person_id"),
        childColumns = arrayOf("person_id"), onDelete = ForeignKey.RESTRICT)), (ForeignKey(
        entity = Company::class, parentColumns = arrayOf("company_id"),
        childColumns = arrayOf("company_id"), onDelete = ForeignKey.RESTRICT)), (ForeignKey(
        entity = Business::class, parentColumns = arrayOf("business_id"),
        childColumns = arrayOf("business_id"), onDelete = ForeignKey.RESTRICT))])
data class Activity(@ColumnInfo(
    name = "description") val description: String, @ColumnInfo(
    name = "type") val type: String, @ColumnInfo(
    name = "date") val date: Date?, @ColumnInfo(
    name = "hour") val hour: Date?, @ColumnInfo(
    name = "person_id") val personId: Long, @ColumnInfo(
    name = "company_id") val companyId: Long, @ColumnInfo(
    name = "business_id") val businessId: Long) {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(
      name = "activity_id")
  var id: Long = 0
}