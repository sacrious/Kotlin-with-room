package com.sacrious.nativapps.domain.company

import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.storage.DatabaseService
import com.sacrious.nativapps.storage.DatabaseService.CompanyCallback
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by sacrious on 1/13/18.
 */
class CompanyController(application: NativAppApplication) : CompanyInteractor {

  init {
    application.getInjector().inject(this)
  }

  @Inject lateinit var mDatabaseService: Lazy<DatabaseService>

  override fun getCompaniesData(companiesData: (companiesList: List<Company>) -> Unit) {
    mDatabaseService.get().getCompaniesData(object : CompanyCallback {
      override fun onCompaniesList(companiesList: List<Company>) {
        companiesData(companiesList)
      }
    })
  }

  override fun saveCompany(companyData: Company) {
    mDatabaseService.get().saveCompany(companyData)
  }
}