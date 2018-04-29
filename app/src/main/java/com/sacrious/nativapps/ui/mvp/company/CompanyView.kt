package com.sacrious.nativapps.ui.mvp.company

import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.ui.mvp.common.BaseView

/**
 * Created by sacrious on 1/13/18.
 */
interface CompanyView : BaseView {
  fun getCompanyData(): Company
  fun showCompaniesList(companiesList: List<Company>)
}