package com.sacrious.nativapps.ui.mvp.home

import com.sacrious.nativapps.ui.mvp.common.BasePresenter

/**
 * Created by sacrious on 1/14/18.
 */
class HomePresenter(homeView: HomeView) : BasePresenter<HomeView>() {
  init {
    attachView(homeView)
  }

  override fun saveObject() {}
}