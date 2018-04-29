package com.sacrious.nativapps.common.di

import com.sacrious.nativapps.common.di.modules.AppCommonModule
import com.sacrious.nativapps.common.di.modules.AppInteractorModule
import com.sacrious.nativapps.domain.activity.ActivityController
import com.sacrious.nativapps.domain.business.BusinessController
import com.sacrious.nativapps.domain.company.CompanyController
import com.sacrious.nativapps.domain.person.PersonController
import com.sacrious.nativapps.ui.view.activity.ActivityFragment
import com.sacrious.nativapps.ui.view.business.BusinessFragment
import com.sacrious.nativapps.ui.view.company.CompanyFragment
import com.sacrious.nativapps.ui.view.home.HomeFragment
import com.sacrious.nativapps.ui.view.person.PersonFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sacrious on 1/13/18.
 */

/** DiComponent for dagger purposes **/
@Singleton
@Component(modules = [(AppInteractorModule::class), (AppCommonModule::class)])
interface DiComponent {
  //region Activities and Fragments

  fun inject(personFragment: PersonFragment)
  fun inject(homeFragment: HomeFragment)
  fun inject(activityFragment: ActivityFragment)
  fun inject(businessFragment: BusinessFragment)
  fun inject(companyFragment: CompanyFragment)
  //endregion
  //region Interactors

  fun inject(personController: PersonController)
  fun inject(companyController: CompanyController)
  fun inject(businessController: BusinessController)
  fun inject(activityController: ActivityController)
  //endregion
}