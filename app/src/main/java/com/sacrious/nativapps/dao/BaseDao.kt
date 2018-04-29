package com.sacrious.nativapps.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update


/**
 * Created by sacrious on 1/13/18.
 */
interface BaseDao<in T> {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg entity: T)

  @Update
  fun update(entity: T)

  @Delete
  fun delete(entity: T)
}