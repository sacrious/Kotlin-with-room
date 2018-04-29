package com.sacrious.nativapps.common.di.modules

import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.domain.activity.ActivityController
import com.sacrious.nativapps.domain.activity.ActivityInteractor
import com.sacrious.nativapps.domain.business.BusinessController
import com.sacrious.nativapps.domain.business.BusinessInteractor
import com.sacrious.nativapps.domain.company.CompanyController
import com.sacrious.nativapps.domain.company.CompanyInteractor
import com.sacrious.nativapps.domain.person.PersonController
import com.sacrious.nativapps.domain.person.PersonInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sacrious on 1/13/18.
 */
@Module
class AppInteractorModule {
  @Provides
  @Singleton
  fun getPersonInteractor(application: NativAppApplication): PersonInteractor {
    return PersonController(application)
  }

  @Provides
  @Singleton
  fun getCompanyInteractor(application: NativAppApplication): CompanyInteractor {
    return CompanyController(application)
  }

  @Provides
  @Singleton
  fun getBusinessInteractor(application: NativAppApplication): BusinessInteractor {
    return BusinessController(application)
  }

  @Provides
  @Singleton
  fun getActivityInteractor(application: NativAppApplication): ActivityInteractor {
    return ActivityController(application)
  }
}