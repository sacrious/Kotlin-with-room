package com.sacrious.nativapps.ui.view.common

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AlertDialog.Builder
import android.view.View
import com.sacrious.nativapps.R
import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.common.di.DiComponent
import com.sacrious.nativapps.ui.mvp.common.BasePresenter
import com.sacrious.nativapps.ui.mvp.common.BaseView
import com.sacrious.nativapps.ui.mvp.common.Component
import com.sacrious.nativapps.ui.view.main.MainActivity
import com.sacrious.nativapps.ui.view.utils.CustomSnackBar

/**
 * Created by sacrious on 1/13/18.
 */
abstract class BaseFragment<P : BasePresenter<*>> : Fragment(), Component<P>, FragmentCallback, BaseView {
  protected var presenter: P? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val diComponent = getInjector()
    diComponent?.let { injectComponent(it) }
    presenter = buildPresenter()
  }

  override fun onDestroy() {
    presenter?.detachView()
    super.onDestroy()
  }

  override fun onDestroyView() {
    presenter?.detachView()
    super.onDestroyView()
  }

  override fun addedSuccessfully(name: String) {
    CustomSnackBar.show(getString(R.string.success), getString(R.string.success_subtitle, name),
        R.color.blue,
        activity?.window?.decorView?.rootView!!,
        NativAppApplication.getContext())
  }

  protected fun showAlert(message: String, fragmentId: Int,
      fragment: BaseFragment<*>) {

    val builder: Builder? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      this.activity?.let {
        AlertDialog.Builder(it,
            android.R.style.Theme_Material_Dialog_Alert)
      }
    } else {
      this.activity?.let { AlertDialog.Builder(it) }
    }
    builder?.setTitle(getString(R.string.alert))?.setMessage(message)?.setPositiveButton(
        android.R.string.yes, { dialog, _ ->
      dialog.dismiss()
      (activity as MainActivity).setNavigationSelectedItem(fragmentId, fragment)
    })?.setIcon(android.R.drawable.ic_dialog_alert)?.setCancelable(false)

    val dialog = builder?.create()!!
    dialog.show()
  }

  fun getFragmentCallback(): FragmentCallback = this

  /**
   * This method gets the injector
   *
   * @return Dependency injector. Returns null if there is no application context available
   */
  private fun getInjector(): DiComponent? {
    val deliveryApplication = NativAppApplication.getContext()
    return deliveryApplication.getInjector()
  }

  /**
   * Injection component. This should be done if there are fields to be injected
   *
   * @param diComponent Dependency injection
   */
  protected abstract fun injectComponent(diComponent: DiComponent)
}