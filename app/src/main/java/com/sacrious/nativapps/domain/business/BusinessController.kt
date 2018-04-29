package com.sacrious.nativapps.domain.business

import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.storage.DatabaseService
import com.sacrious.nativapps.storage.DatabaseService.BusinessCallback
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by sacrious on 1/13/18.
 */
class BusinessController(application: NativAppApplication) : BusinessInteractor {

  init {
    application.getInjector().inject(this)
  }

  @Inject lateinit var mDatabaseService: Lazy<DatabaseService>

  override fun saveBusiness(business: Business) {
    mDatabaseService.get().saveBusiness(business)
  }

  override fun getBusinessesData(businessesData: (businessesList: List<Business>) -> Unit) {
    mDatabaseService.get().getBusinessesData(object : BusinessCallback {
      override fun onBusinessesList(businessesList: List<Business>) {
        businessesData(businessesList)
      }
    })
  }

  override fun getBusinessesByStatus(status: Boolean,
      businessesData: (businessesList: List<Business>) -> Unit) {
    mDatabaseService.get().getBusinessesByStatus(status, object : BusinessCallback {
      override fun onBusinessesList(businessesList: List<Business>) {
        businessesData(businessesList)
      }
    })
  }
}