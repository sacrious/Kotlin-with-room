package com.sacrious.nativapps.ui.mvp.activity

import com.sacrious.nativapps.common.utils.DateFormatterUtils
import com.sacrious.nativapps.domain.activity.ActivityInteractor
import com.sacrious.nativapps.domain.business.BusinessInteractor
import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.domain.person.PersonInteractor
import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.ui.mvp.common.BasePresenter

/**
 * Created by sacrious on 1/13/18.
 */
class ActivityPresenter(activityView: ActivityView,
    private val mActivityInteractor: ActivityInteractor,
    private val mBusinessInteractor: BusinessInteractor,
    private val mPersonInteractor: PersonInteractor,
    private val mCompanyInteractor: CompanyInteractor) : BasePresenter<ActivityView>() {
  init {
    attachView(activityView)
  }

  fun start() {
    view?.updateDate(todayDate())
    view?.updateHour(todayHour())
    mPersonInteractor.getPeopleData { peopleList ->
      if (peopleList.isEmpty()) {
        view?.neededPerson()
      } else {
        view?.renderPeople(peopleList)
        mCompanyInteractor.getCompaniesData { companiesList ->
          if (companiesList.isEmpty()) {
            view?.needCompany()
          } else {
            view?.renderCompanies(companiesList)
            mBusinessInteractor.getBusinessesByStatus(true) { businessesList ->
              if (businessesList.isEmpty()) {
                view?.needBusiness()
              } else {
                view?.renderBusinesses(businessesList)
              }
            }
          }
        }
      }
    }
  }

  fun getActivities() {
    mActivityInteractor.getActivitiesData(activitiesData)
  }

  private val activitiesData: (activitiesList: List<Activity>) -> Unit = { activitiesList: List<Activity> ->
    view?.showActivitiesList(activitiesList)
  }

  override fun calculateDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
    super.calculateDate(year, monthOfYear, dayOfMonth)
    view?.updateDate(DateFormatterUtils.convertDateToString(mCalendar.time))

  }

  override fun calculateTime(hour: Int, minute: Int) {
    super.calculateTime(hour, minute)
    view?.updateHour(DateFormatterUtils.convertHourToString(mCalendar.time))
  }

  override fun saveObject() {
    val activityData = view?.getActivityData()
    activityData?.let { mActivityInteractor.saveActivity(it) }
    activityData?.description?.let { view?.addedSuccessfully(it) }
    getActivities()
  }
}