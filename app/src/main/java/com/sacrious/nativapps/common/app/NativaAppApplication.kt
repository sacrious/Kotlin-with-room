package com.sacrious.nativapps.common.app

import android.app.Application
import com.sacrious.nativapps.common.di.DaggerDiComponent
import com.sacrious.nativapps.common.di.DiComponent
import com.sacrious.nativapps.common.di.modules.AppCommonModule
import com.sacrious.nativapps.common.di.modules.AppInteractorModule

/**
 * Created by sacrious on 1/13/18.
 */

/** Application **/
class NativAppApplication : Application() {

  /** Context used on the app **/
  companion object {
    private lateinit var instance: NativAppApplication

    fun getContext(): NativAppApplication {
      return instance
    }
  }

  protected lateinit var mDiComponent: DiComponent

  override fun onCreate() {
    super.onCreate()
    instance = this
    mDiComponent = createComponent()
  }

  /**
   * Create dependency injection component for dagger purposes
   */
  private fun createComponent(): DiComponent {
    return DaggerDiComponent.builder()
        .appInteractorModule(AppInteractorModule())
        .appCommonModule(AppCommonModule(this))
        .build()
  }

  /**
   * This method allows to access to the injector of classes of dagger inside the app
   *
   * @return the DiComponent that injects the classes in the app
   */
  fun getInjector(): DiComponent {
    return mDiComponent
  }
}
