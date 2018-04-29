package com.sacrious.nativapps.domain.activity

import com.sacrious.nativapps.model.Activity

/**
 * Created by sacrious on 1/14/18.
 */
interface ActivityInteractor {
  fun saveActivity(activity: Activity)
  fun getActivitiesData(activitiesData: (activities: List<Activity>) -> Unit)
}