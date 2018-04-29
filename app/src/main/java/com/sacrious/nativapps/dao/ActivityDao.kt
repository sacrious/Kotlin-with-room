package com.sacrious.nativapps.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.sacrious.nativapps.model.Activity

/**
 * Created by sacrious on 1/14/18.
 */
@Dao
interface ActivityDao : BaseDao<Activity> {
  @Query("SELECT * FROM activity")
  fun getAll(): List<Activity>
}