package com.sacrious.nativapps.ui.mvp.business

import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.mvp.common.BaseView

/**
 * Created by sacrious on 1/13/18.
 */
interface BusinessView : BaseView {
  fun updateDate(date: String?)
  fun renderPeople(peopleList: List<Person>)
  fun renderCompanies(companiesList: List<Company>)
  fun neededPerson()
  fun needCompany()
  fun getBusinessData(): Business
  fun showBusinessList(businessesList: List<Business>)
}