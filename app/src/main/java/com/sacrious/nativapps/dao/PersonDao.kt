package com.sacrious.nativapps.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.sacrious.nativapps.model.Person


/**
 * Created by sacrious on 1/13/18.
 */
@Dao
interface PersonDao : BaseDao<Person> {
  @Query("SELECT * FROM person")
  fun getAll(): List<Person>
}