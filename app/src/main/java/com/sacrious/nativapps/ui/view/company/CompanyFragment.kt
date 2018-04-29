package com.sacrious.nativapps.ui.view.company

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.sacrious.nativapps.R
import com.sacrious.nativapps.common.di.DiComponent
import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.ui.adapter.CompaniesListAdapter
import com.sacrious.nativapps.ui.common.extension.validateField
import com.sacrious.nativapps.ui.mvp.company.CompanyPresenter
import com.sacrious.nativapps.ui.mvp.company.CompanyView
import com.sacrious.nativapps.ui.view.common.BaseFragment
import com.sacrious.nativapps.ui.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_company.address_txt_input_ly
import kotlinx.android.synthetic.main.fragment_company.name_txt_input_ly
import kotlinx.android.synthetic.main.fragment_company.phone_txt_input_ly
import javax.inject.Inject
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


/**
 * A simple [Fragment] subclass.
 */
class CompanyFragment : BaseFragment<CompanyPresenter>(), CompanyView {

  @Inject lateinit var mCompanyInteractor: CompanyInteractor

  override fun getCompanyData(): Company = Company(
      name = name_txt_input_ly.editText?.text.toString(),
      address = address_txt_input_ly.editText?.text.toString(),
      phone = phone_txt_input_ly.editText?.text.toString())

  override fun injectComponent(diComponent: DiComponent) {
    diComponent.inject(this)
  }

  override fun onAdd() {
    name_txt_input_ly.validateField()
    address_txt_input_ly.validateField()
    phone_txt_input_ly.validateField()
    presenter?.validateFields(listOf(name_txt_input_ly, address_txt_input_ly, phone_txt_input_ly))
  }

  override fun onShowList() {
    presenter?.getCompanies()
  }

  override fun showCompaniesList(companiesList: List<Company>) {
    (activity as MainActivity).renderAdapter(CompaniesListAdapter(companiesList))

  }

  override fun buildPresenter(): CompanyPresenter = CompanyPresenter(this, mCompanyInteractor)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_company, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    onShowList()
    (activity as MainActivity).showButtons(true)

    phone_txt_input_ly.editText?.setOnEditorActionListener(
        TextView.OnEditorActionListener { _, p1, _ ->
          val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
          imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
          if (p1 == EditorInfo.IME_ACTION_SEND) {
            onAdd()
            return@OnEditorActionListener true
          }
          false
        })
  }
}
