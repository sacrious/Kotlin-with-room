package com.sacrious.nativapps.ui.mvp.person

import com.sacrious.nativapps.domain.person.PersonInteractor
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.mvp.common.BasePresenter

/**
 * Created by sacrious on 1/13/18.
 */
class PersonPresenter(personView: PersonView,
    private val mPersonInteractor: PersonInteractor) : BasePresenter<PersonView>() {
  init {
    attachView(personView)
  }

  override fun saveObject() {
    val personData = view?.getPersonData()
    personData?.let { mPersonInteractor.savePerson(it) }
    personData?.name?.let { view?.addedSuccessfully(it) }
    getPeople()
  }

  fun getPeople() {
    mPersonInteractor.getPeopleData(peopleData)
  }


  private val peopleData: (peopleList: List<Person>) -> Unit = { peopleList: List<Person> ->
    view?.showPeopleList(peopleList)
  }

}