package com.sacrious.nativapps.storage

import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person

/**
 * Created by sacrious on 1/13/18.
 */
interface DatabaseService {
  /** save the person data **/
  fun savePerson(personData: Person)

  /** get all people data **/
  fun getPeopleData(peopleCallback: PeopleCallback)

  /** save the company data **/
  fun saveCompany(companyData: Company)

  /** get all companies data **/
  fun getCompaniesData(companyCallback: CompanyCallback)

  /** save business data **/
  fun saveBusiness(business: Business)

  /** get all business data **/
  fun getBusinessesData(businessCallback: BusinessCallback)

  /** get all business data by status (true, false) **/
  fun getBusinessesByStatus(status: Boolean, businessCallback: BusinessCallback)

  /** save activity data **/
  fun saveActivity(activity: Activity)

  /** get all activities data **/
  fun getActivitiesData(activityCallback: ActivityCallback)

  interface PeopleCallback {
    fun onPeopleList(peopleList: List<Person>)
  }

  interface CompanyCallback {
    fun onCompaniesList(companiesList: List<Company>)
  }

  interface BusinessCallback {
    fun onBusinessesList(businessesList: List<Business>)
  }

  interface ActivityCallback {
    fun onActivitiesList(activitiesList: List<Activity>)
  }
}