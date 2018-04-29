package com.sacrious.nativapps.ui.mvp.common

/**
 * Created by sacrious on 1/13/18.
 */
interface Component<out T : BasePresenter<*>> {
  /**
   * This method should return an instance of the Presenter
   *
   * @return Instance of the Presenter
   */
  abstract fun buildPresenter(): T
}