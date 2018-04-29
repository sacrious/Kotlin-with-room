package com.sacrious.nativapps.ui.mvp.business

import com.sacrious.nativapps.common.utils.DateFormatterUtils
import com.sacrious.nativapps.domain.business.BusinessInteractor
import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.domain.person.PersonInteractor
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.ui.mvp.common.BasePresenter

/**
 * Created by sacrious on 1/13/18.
 */
class BusinessPresenter(businessView: BusinessView,
    private val mBusinessInteractor: BusinessInteractor,
    private val mPersonInteractor: PersonInteractor,
    private val mCompanyInteractor: CompanyInteractor) : BasePresenter<BusinessView>() {
  init {
    attachView(businessView)
  }

  fun start() {
    view?.updateDate(todayDate())
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
          }
        }
      }
    }
  }

  override fun calculateDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
    super.calculateDate(year, monthOfYear, dayOfMonth)
    view?.updateDate(DateFormatterUtils.convertDateToString(mCalendar.time))
  }

  override fun saveObject() {
    val businessData = view?.getBusinessData()
    businessData?.let { mBusinessInteractor.saveBusiness(it) }
    businessData?.title?.let { view?.addedSuccessfully(it) }
    getBusinesses()
  }

  fun getBusinesses() {
    mBusinessInteractor.getBusinessesData(businessesData)
  }

  private val businessesData: (businessesList: List<Business>) -> Unit = { businessesList: List<Business> ->
    view?.showBusinessList(businessesList)
  }
}