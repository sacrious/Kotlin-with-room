package com.sacrious.nativapps.ui.view.business

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.sacrious.nativapps.R
import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.common.di.DiComponent
import com.sacrious.nativapps.common.utils.DateFormatterUtils
import com.sacrious.nativapps.domain.business.BusinessInteractor
import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.domain.person.PersonInteractor
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.adapter.BusinessesListAdapter
import com.sacrious.nativapps.ui.adapter.CompanyAdapter
import com.sacrious.nativapps.ui.adapter.PeopleAdapter
import com.sacrious.nativapps.ui.common.extension.validateField
import com.sacrious.nativapps.ui.mvp.business.BusinessPresenter
import com.sacrious.nativapps.ui.mvp.business.BusinessView
import com.sacrious.nativapps.ui.view.common.BaseFragment
import com.sacrious.nativapps.ui.view.company.CompanyFragment
import com.sacrious.nativapps.ui.view.main.MainActivity
import com.sacrious.nativapps.ui.view.person.PersonFragment
import kotlinx.android.synthetic.main.fragment_business.company_sp
import kotlinx.android.synthetic.main.fragment_business.date_txt_input_ly
import kotlinx.android.synthetic.main.fragment_business.description_txt_input_ly
import kotlinx.android.synthetic.main.fragment_business.people_sp
import kotlinx.android.synthetic.main.fragment_business.status_switch
import kotlinx.android.synthetic.main.fragment_business.title_txt_input_ly
import kotlinx.android.synthetic.main.fragment_business.value_txt_input_ly
import java.util.Calendar
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class BusinessFragment : BaseFragment<BusinessPresenter>(), BusinessView {

  @Inject lateinit var mBusinessInteractor: BusinessInteractor
  @Inject lateinit var mPersonInteractor: PersonInteractor
  @Inject lateinit var mCompanyInteractor: CompanyInteractor

  var date: OnDateSetListener = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
    presenter?.calculateDate(year, monthOfYear, dayOfMonth)
  }

  override fun injectComponent(diComponent: DiComponent) {
    diComponent.inject(this)
  }

  override fun onAdd() {
    title_txt_input_ly.validateField()
    description_txt_input_ly.validateField()
    value_txt_input_ly.validateField()
    presenter?.validateFields(
        listOf(title_txt_input_ly, description_txt_input_ly, value_txt_input_ly))
  }

  override fun getBusinessData(): Business = Business(
      title = title_txt_input_ly.editText?.text.toString(),
      description = description_txt_input_ly.editText?.text.toString(),
      date = DateFormatterUtils.convertStringToDate(date_txt_input_ly.editText?.text.toString()),
      status = status_switch.isChecked,
      personId = (people_sp.selectedItem as Person).id,
      companyId = (company_sp.selectedItem as Company).id,
      value = value_txt_input_ly.editText?.text.toString().toDouble())

  override fun onShowList() {
    presenter?.getBusinesses()
  }

  override fun buildPresenter(): BusinessPresenter = BusinessPresenter(this, mBusinessInteractor,
      mPersonInteractor, mCompanyInteractor)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_business, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter?.start()
    onShowList()
    (activity as MainActivity).showButtons(true)

    date_txt_input_ly.editText?.setOnClickListener({
      DatePickerDialog(context, date, presenter?.mCalendar?.get(Calendar.YEAR)!!,
          presenter?.mCalendar?.get(Calendar.MONTH)!!,
          presenter?.mCalendar?.get(Calendar.DAY_OF_MONTH)!!).show()
    })

    value_txt_input_ly.editText?.setOnEditorActionListener(
        TextView.OnEditorActionListener { _, p1, _ ->
          if (p1 == EditorInfo.IME_ACTION_SEND) {
            onAdd()
            return@OnEditorActionListener true
          }
          false
        })
  }

  override fun updateDate(date: String?) {
    date_txt_input_ly.editText?.setText(date)
  }

  override fun renderPeople(peopleList: List<Person>) {
    people_sp.adapter = PeopleAdapter(NativAppApplication.getContext(), peopleList)
  }

  override fun renderCompanies(companiesList: List<Company>) {
    company_sp.adapter = CompanyAdapter(NativAppApplication.getContext(), companiesList)
  }

  override fun neededPerson() {
    showAlert(getString(R.string.should_create_person), R.id.nav_person, PersonFragment())
  }

  override fun needCompany() {
    showAlert(getString(R.string.should_create_company), R.id.nav_company, CompanyFragment())
  }

  override fun showBusinessList(businessesList: List<Business>) {
    (activity as MainActivity).renderAdapter(BusinessesListAdapter(businessesList))
  }
}
