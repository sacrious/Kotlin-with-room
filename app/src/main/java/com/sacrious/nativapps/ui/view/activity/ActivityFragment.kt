package com.sacrious.nativapps.ui.view.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
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
import com.sacrious.nativapps.domain.activity.ActivityInteractor
import com.sacrious.nativapps.domain.business.BusinessInteractor
import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.domain.person.PersonInteractor
import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.adapter.ActivitiesListAdapter
import com.sacrious.nativapps.ui.adapter.BusinessAdapter
import com.sacrious.nativapps.ui.adapter.CompanyAdapter
import com.sacrious.nativapps.ui.adapter.PeopleAdapter
import com.sacrious.nativapps.ui.common.extension.validateField
import com.sacrious.nativapps.ui.mvp.activity.ActivityPresenter
import com.sacrious.nativapps.ui.mvp.activity.ActivityView
import com.sacrious.nativapps.ui.view.business.BusinessFragment
import com.sacrious.nativapps.ui.view.common.BaseFragment
import com.sacrious.nativapps.ui.view.company.CompanyFragment
import com.sacrious.nativapps.ui.view.main.MainActivity
import com.sacrious.nativapps.ui.view.person.PersonFragment
import kotlinx.android.synthetic.main.fragment_activity.business_sp
import kotlinx.android.synthetic.main.fragment_activity.company_sp
import kotlinx.android.synthetic.main.fragment_activity.date_txt_input_ly
import kotlinx.android.synthetic.main.fragment_activity.description_txt_input_ly
import kotlinx.android.synthetic.main.fragment_activity.people_sp
import kotlinx.android.synthetic.main.fragment_activity.time_txt_input_ly
import kotlinx.android.synthetic.main.fragment_activity.type_txt_input_ly
import java.util.Calendar
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ActivityFragment : BaseFragment<ActivityPresenter>(), ActivityView {

  @Inject lateinit var mActivityInteractor: ActivityInteractor
  @Inject lateinit var mBusinessInteractor: BusinessInteractor
  @Inject lateinit var mPersonInteractor: PersonInteractor
  @Inject lateinit var mCompanyInteractor: CompanyInteractor

  var date: OnDateSetListener = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
    presenter?.calculateDate(year, monthOfYear, dayOfMonth)
  }

  var time: OnTimeSetListener = OnTimeSetListener { _, hour, minute ->
    presenter?.calculateTime(hour, minute)
  }

  override fun injectComponent(diComponent: DiComponent) {
    diComponent.inject(this)
  }

  override fun onAdd() {
    description_txt_input_ly.validateField()
    type_txt_input_ly.validateField()
    presenter?.validateFields(listOf(description_txt_input_ly, type_txt_input_ly))
  }

  override fun onShowList() {
    presenter?.getActivities()
  }

  override fun buildPresenter(): ActivityPresenter = ActivityPresenter(this, mActivityInteractor,
      mBusinessInteractor, mPersonInteractor, mCompanyInteractor)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_activity, container, false)
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

    time_txt_input_ly.editText?.setOnClickListener({
      TimePickerDialog(context, time, presenter?.mCalendar?.get(Calendar.HOUR_OF_DAY)!!,
          presenter?.mCalendar?.get(Calendar.MINUTE)!!, false).show()
    })

    type_txt_input_ly.editText?.setOnEditorActionListener(
        TextView.OnEditorActionListener { _, p1, _ ->
          if (p1 == EditorInfo.IME_ACTION_SEND) {
            onAdd()
            return@OnEditorActionListener true
          }
          false
        })
  }

  override fun neededPerson() {
    showAlert(getString(R.string.should_create_person), R.id.nav_person, PersonFragment())
  }

  override fun needCompany() {
    showAlert(getString(R.string.should_create_company), R.id.nav_company, CompanyFragment())
  }

  override fun needBusiness() {
    showAlert(getString(R.string.should_create_business), R.id.nav_business, BusinessFragment())
  }

  override fun renderCompanies(companiesList: List<Company>) {
    company_sp.adapter = CompanyAdapter(NativAppApplication.getContext(), companiesList)
  }

  override fun renderPeople(peopleList: List<Person>) {
    people_sp.adapter = PeopleAdapter(NativAppApplication.getContext(), peopleList)
  }

  override fun renderBusinesses(businessesList: List<Business>) {
    business_sp.adapter = BusinessAdapter(NativAppApplication.getContext(), businessesList)
  }

  override fun showActivitiesList(activitiesList: List<Activity>) {
    (activity as MainActivity).renderAdapter(ActivitiesListAdapter(activitiesList))
  }

  override fun updateDate(date: String?) {
    date_txt_input_ly.editText?.setText(date)
  }

  override fun updateHour(todayHour: String?) {
    time_txt_input_ly.editText?.setText(todayHour)
  }

  override fun getActivityData(): Activity = Activity(
      description = description_txt_input_ly.editText?.text.toString(),
      type = type_txt_input_ly.editText?.text.toString(),
      date = DateFormatterUtils.convertStringToDate(date_txt_input_ly.editText?.text.toString()),
      hour = DateFormatterUtils.convertStringToHour(time_txt_input_ly.editText?.text.toString()),
      personId = (people_sp.selectedItem as Person).id,
      companyId = (company_sp.selectedItem as Company).id,
      businessId = (business_sp.selectedItem as Business).id)
}
