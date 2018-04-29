package com.sacrious.nativapps.ui.mvp.company

import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.ui.mvp.common.BasePresenter

/**
 * Created by sacrious on 1/13/18.
 */
class CompanyPresenter(companyView: CompanyView,
    private val mCompanyInteractor: CompanyInteractor) : BasePresenter<CompanyView>() {
  init {
    attachView(companyView)
  }

  override fun saveObject() {
    val companyData = view?.getCompanyData()
    companyData?.let { mCompanyInteractor.saveCompany(it) }
    companyData?.name?.let { view?.addedSuccessfully(it) }
    getCompanies()
  }

  fun getCompanies() {
    mCompanyInteractor.getCompaniesData(companiesData)
  }

  private val companiesData: (companiesList: List<Company>) -> Unit = { companiesList: List<Company> ->
    view?.showCompaniesList(companiesList)
  }
}