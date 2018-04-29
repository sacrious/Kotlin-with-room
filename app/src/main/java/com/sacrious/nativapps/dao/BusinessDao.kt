package com.sacrious.nativapps.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.sacrious.nativapps.model.Business

/**
 * Created by sacrious on 1/13/18.
 */
@Dao
interface BusinessDao : BaseDao<Business> {
  @Query("SELECT * FROM business")
  fun getAll(): List<Business>

  @Query("SELECT * FROM business WHERE status = :status")
  fun getByStatus(status: Boolean): List<Business>
}