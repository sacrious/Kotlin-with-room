package com.sacrious.nativapps.domain.company

import com.sacrious.nativapps.model.Company

/**
 * Created by sacrious on 1/13/18.
 */
interface CompanyInteractor {
  fun saveCompany(companyData: Company)

  fun getCompaniesData(companiesData: (companiesList: List<Company>) -> Unit)
}