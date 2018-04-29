package com.sacrious.nativapps.domain.person

import com.sacrious.nativapps.model.Person

/**
 * Created by sacrious on 1/13/18.
 */
interface PersonInteractor {
  fun savePerson(personData: Person)

  fun getPeopleData(peopleData: (peopleList: List<Person>) -> Unit)
}