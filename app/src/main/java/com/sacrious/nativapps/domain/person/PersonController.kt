package com.sacrious.nativapps.domain.person

import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.storage.DatabaseService
import com.sacrious.nativapps.storage.DatabaseService.PeopleCallback
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by sacrious on 1/13/18.
 */
class PersonController(application: NativAppApplication) : PersonInteractor {

  init {
    application.getInjector().inject(this)
  }

  @Inject lateinit var mDatabaseService: Lazy<DatabaseService>

  override fun savePerson(personData: Person) {
    mDatabaseService.get().savePerson(personData)
  }

  override fun getPeopleData(peopleData: (peopleList: List<Person>) -> Unit) {
    mDatabaseService.get().getPeopleData(object : PeopleCallback {
      override fun onPeopleList(peopleList: List<Person>) {
        peopleData(peopleList)
      }
    })
  }
}

