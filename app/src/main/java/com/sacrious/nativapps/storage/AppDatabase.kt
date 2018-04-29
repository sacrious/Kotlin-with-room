package com.sacrious.nativapps.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.sacrious.nativapps.common.utils.Converters
import com.sacrious.nativapps.dao.ActivityDao
import com.sacrious.nativapps.dao.BusinessDao
import com.sacrious.nativapps.dao.CompanyDao
import com.sacrious.nativapps.dao.PersonDao
import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person

/**
 * Created by sacrious on 1/13/18.
 */

/** Here is where de daos should be **/
@Database(entities = [(Person::class), (Company::class), (Business::class), (Activity::class)],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun personDao(): PersonDao
  abstract fun companyDao(): CompanyDao
  abstract fun businessDao(): BusinessDao
  abstract fun activityDao(): ActivityDao
}