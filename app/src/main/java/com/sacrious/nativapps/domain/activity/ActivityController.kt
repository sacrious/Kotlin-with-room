package com.sacrious.nativapps.domain.activity

import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.storage.DatabaseService
import com.sacrious.nativapps.storage.DatabaseService.ActivityCallback
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by sacrious on 1/14/18.
 */
class ActivityController(application: NativAppApplication) : ActivityInteractor {

  init {
    application.getInjector().inject(this)
  }

  @Inject lateinit var mDatabaseService: Lazy<DatabaseService>

  override fun saveActivity(activity: Activity) {
    mDatabaseService.get().saveActivity(activity)
  }

  override fun getActivitiesData(activitiesData: (activities: List<Activity>) -> Unit) {
    mDatabaseService.get().getActivitiesData(object : ActivityCallback {
      override fun onActivitiesList(activitiesList: List<Activity>) {
        activitiesData(activitiesList)
      }
    })
  }
}