package com.sacrious.nativapps.ui.common.extension

import android.support.design.widget.TextInputLayout
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sacrious.nativapps.R

/**
 * Created by sacrious on 1/13/18.
 */
fun View.show() {
  visibility = View.VISIBLE
}

fun View.hide(keepSpace: Boolean) {
  visibility = if (keepSpace) {
    View.INVISIBLE
  } else {
    View.GONE
  }
}

fun TextInputLayout.validateEmail() {
  error = if (Patterns.EMAIL_ADDRESS.matcher(editText?.text).matches()) {
    null
  } else {
    resources.getString(R.string.invalid_email)
  }
}

fun TextInputLayout.validateField() {
  error = if (!editText?.text?.trim()?.isEmpty()!!) {
    null
  } else {
    resources.getString(R.string.invalid_field)
  }
}

fun ViewGroup.inflate(layout_item_filter_header: Int): View {
  return LayoutInflater.from(context).inflate(layout_item_filter_header, this, false)
}