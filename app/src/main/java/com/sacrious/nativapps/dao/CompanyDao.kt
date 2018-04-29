package com.sacrious.nativapps.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.sacrious.nativapps.model.Company

/**
 * Created by sacrious on 1/13/18.
 */
@Dao
interface CompanyDao : BaseDao<Company> {
  @Query("SELECT * FROM company")
  fun getAll(): List<Company>
}