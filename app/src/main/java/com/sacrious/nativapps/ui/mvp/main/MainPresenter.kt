package com.sacrious.nativapps.ui.mvp.main

import com.sacrious.nativapps.ui.mvp.common.BasePresenter

/**
 * Created by sacrious on 1/13/18.
 */
class MainPresenter(mainView: MainView) : BasePresenter<MainView>() {

  init {
    attachView(mainView)
  }

  override fun saveObject() {}
}