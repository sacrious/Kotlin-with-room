package com.sacrious.nativapps.ui.mvp.activity

import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.mvp.common.BaseView

/**
 * Created by sacrious on 1/13/18.
 */
interface ActivityView : BaseView {
  fun neededPerson()
  fun needCompany()
  fun renderCompanies(companiesList: List<Company>)
  fun renderPeople(peopleList: List<Person>)
  fun needBusiness()
  fun renderBusinesses(businessesList: List<Business>)
  fun showActivitiesList(activitiesList: List<Activity>)
  fun updateDate(date: String?)
  fun updateHour(todayHour: String?)
  fun getActivityData(): Activity
}