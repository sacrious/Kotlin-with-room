package com.sacrious.nativapps.ui.view.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sacrious.nativapps.ui.mvp.common.BasePresenter
import com.sacrious.nativapps.ui.mvp.common.Component

/**
 * Created by sacrious on 1/13/18.
 */
abstract class BaseActivity<P : BasePresenter<*>> : AppCompatActivity(), Component<P> {
  protected var presenter: P? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = buildPresenter()

  }

  override fun onDestroy() {
    super.onDestroy()
    presenter?.detachView()
  }
}