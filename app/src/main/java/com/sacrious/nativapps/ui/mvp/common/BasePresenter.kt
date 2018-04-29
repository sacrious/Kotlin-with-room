package com.sacrious.nativapps.ui.mvp.common

import android.support.design.widget.TextInputLayout
import com.sacrious.nativapps.common.utils.DateFormatterUtils
import java.util.Calendar

/**
 * Created by sacrious on 1/13/18.
 */
abstract class BasePresenter<V : BaseView> {

  protected var view: V? = null

  var mCalendar: Calendar = Calendar.getInstance()

  fun attachView(view: V) {
    this.view = view
  }

  fun detachView() {
    this.view = null
  }


  fun todayDate(): String? {
    val currentDate = System.currentTimeMillis()
    mCalendar.timeInMillis = currentDate
    return DateFormatterUtils.convertLongToString(currentDate)
  }

  fun todayHour(): String? {
    val currentDate = System.currentTimeMillis()
    mCalendar.timeInMillis = currentDate
    return DateFormatterUtils.convertLongToHour(currentDate)
  }

  open fun calculateDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
    mCalendar.set(Calendar.YEAR, year)
    mCalendar.set(Calendar.MONTH, monthOfYear)
    mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
  }

  open fun calculateTime(hour: Int, minute: Int) {
    mCalendar.set(Calendar.HOUR_OF_DAY, hour)
    mCalendar.set(Calendar.MINUTE, minute)
  }

  fun validateFields(textLayoutList: List<TextInputLayout>): Boolean {
    return if (textLayoutList.find { it.error != null } == null) {
      saveObject()
      textLayoutList.forEach { it.editText?.setText("") }
      true
    } else {
      false
    }
  }

  abstract fun saveObject()
}