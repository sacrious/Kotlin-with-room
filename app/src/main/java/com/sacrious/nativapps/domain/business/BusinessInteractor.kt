package com.sacrious.nativapps.domain.business

import com.sacrious.nativapps.model.Business

/**
 * Created by sacrious on 1/13/18.
 */
interface BusinessInteractor {
  fun saveBusiness(business: Business)
  fun getBusinessesData(businessesData: (businessesList: List<Business>) -> Unit)
  fun getBusinessesByStatus(status: Boolean,
      businessesData: (businessesList: List<Business>) -> Unit)
}