package com.sacrious.nativapps.storage

import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.storage.DatabaseService.ActivityCallback
import com.sacrious.nativapps.storage.DatabaseService.BusinessCallback
import com.sacrious.nativapps.storage.DatabaseService.CompanyCallback
import com.sacrious.nativapps.storage.DatabaseService.PeopleCallback
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by sacrious on 1/13/18.
 */
class DatabaseManager(private val db: AppDatabase) : DatabaseService {


  override fun getPeopleData(peopleCallback: PeopleCallback) {
    doAsync {
      val peopleList = db.personDao().getAll()
      uiThread { peopleCallback.onPeopleList(peopleList) }
    }
  }

  override fun savePerson(personData: Person) {
    doAsync {
      db.personDao().insert(personData)
    }
  }

  override fun saveCompany(companyData: Company) {
    doAsync {
      db.companyDao().insert(companyData)
    }
  }

  override fun getCompaniesData(companyCallback: CompanyCallback) {
    doAsync {
      val companiesList = db.companyDao().getAll()
      uiThread { companyCallback.onCompaniesList(companiesList) }
    }
  }

  override fun saveBusiness(business: Business) {
    doAsync {
      db.businessDao().insert(business)
    }
  }

  override fun getBusinessesData(businessCallback: BusinessCallback) {
    doAsync {
      val businessesList = db.businessDao().getAll()
      uiThread { businessCallback.onBusinessesList(businessesList) }
    }
  }

  override fun getBusinessesByStatus(status: Boolean, businessCallback: BusinessCallback) {
    doAsync {
      val businessesList = db.businessDao().getByStatus(status)
      uiThread { businessCallback.onBusinessesList(businessesList) }
    }
  }

  override fun saveActivity(activity: Activity) {
    doAsync {
      db.activityDao().insert(activity)
    }
  }

  override fun getActivitiesData(activityCallback: ActivityCallback) {
    doAsync {
      val activitiesList = db.activityDao().getAll()
      uiThread { activityCallback.onActivitiesList(activitiesList) }
    }
  }
}