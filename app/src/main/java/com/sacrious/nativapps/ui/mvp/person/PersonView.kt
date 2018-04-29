package com.sacrious.nativapps.ui.mvp.person

import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.mvp.common.BaseView

/**
 * Created by sacrious on 1/13/18.
 */
interface PersonView : BaseView {
  fun getPersonData(): Person
  fun showPeopleList(peopleList: List<Person>)
}