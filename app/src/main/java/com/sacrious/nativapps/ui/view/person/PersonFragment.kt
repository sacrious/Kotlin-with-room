package com.sacrious.nativapps.ui.view.person

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import com.sacrious.nativapps.R
import com.sacrious.nativapps.common.di.DiComponent
import com.sacrious.nativapps.domain.person.PersonInteractor
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.adapter.PeopleListAdapter
import com.sacrious.nativapps.ui.common.extension.validateEmail
import com.sacrious.nativapps.ui.common.extension.validateField
import com.sacrious.nativapps.ui.mvp.person.PersonPresenter
import com.sacrious.nativapps.ui.mvp.person.PersonView
import com.sacrious.nativapps.ui.view.common.BaseFragment
import com.sacrious.nativapps.ui.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_person.email_txt_input_ly
import kotlinx.android.synthetic.main.fragment_person.name_txt_input_ly
import kotlinx.android.synthetic.main.fragment_person.phone_txt_input_ly
import javax.inject.Inject
import android.arch.persistence.room.Room as RoomDb


/**
 * A simple [Fragment] subclass.
 */
class PersonFragment : BaseFragment<PersonPresenter>(), PersonView {

  @Inject lateinit var mPersonInteractor: PersonInteractor

  override fun injectComponent(diComponent: DiComponent) {
    diComponent.inject(this)
  }

  override fun getPersonData(): Person = Person(name = name_txt_input_ly.editText?.text.toString(),
      phone = phone_txt_input_ly.editText?.text.toString(),
      email = email_txt_input_ly.editText?.text.toString())


  override fun onAdd() {
    name_txt_input_ly.validateField()
    phone_txt_input_ly.validateField()
    email_txt_input_ly.validateEmail()
    presenter?.validateFields(listOf(email_txt_input_ly, name_txt_input_ly, phone_txt_input_ly))
  }

  override fun onShowList() {
    presenter?.getPeople()
  }

  override fun showPeopleList(peopleList: List<Person>) {
    (activity as MainActivity).renderAdapter(PeopleListAdapter(peopleList))
  }

  override fun buildPresenter(): PersonPresenter = PersonPresenter(this, mPersonInteractor)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_person, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    onShowList()
    (activity as MainActivity).showButtons(true)

    email_txt_input_ly.editText?.setOnEditorActionListener(OnEditorActionListener { _, p1, _ ->
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
