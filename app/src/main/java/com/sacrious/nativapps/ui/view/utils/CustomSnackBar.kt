package com.sacrious.nativapps.ui.view.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.sacrious.nativapps.R

/**
 * Created by sacrious on 1/13/18.
 */
class CustomSnackBar {
  companion object {
    fun show(mTitle: String, mMessage: String, backgroundColor: Int, mParentLayout: View,
        context: Context) {
      show(mTitle, mMessage, backgroundColor, mParentLayout, context, null)
    }

    private fun show(mTitle: String, mMessage: String, backgroundColor: Int, mParentLayout: View,
        context: Context, listener: View.OnClickListener?) {
      val textSize1 = context.resources.getDimensionPixelSize(R.dimen.standard_text_size)
      val textSize2 = context.resources.getDimensionPixelSize(R.dimen.small_text_size)

      val span1 = SpannableString(mTitle)
      span1.setSpan(AbsoluteSizeSpan(textSize1), 0, mTitle.length, SPAN_INCLUSIVE_INCLUSIVE)

      val span2 = SpannableString(mMessage)
      span2.setSpan(AbsoluteSizeSpan(textSize2), 0, mMessage.length, SPAN_INCLUSIVE_INCLUSIVE)

      val finalText = TextUtils.concat(span1, "\n", span2)

      val mSnackBar = Snackbar.make(mParentLayout, finalText, Snackbar.LENGTH_LONG)
      val mSnackBarView = mSnackBar.view
      val textView = mSnackBarView.findViewById<View>(
          android.support.design.R.id.snackbar_text) as TextView
      textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher, 0, 0, 0)
      textView.compoundDrawablePadding = context.resources.getDimensionPixelOffset(
          R.dimen.snack_bar_icon_padding)
      textView.maxLines = 4
      mSnackBarView.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
      val mLayoutParams = mSnackBarView.layoutParams as FrameLayout.LayoutParams
      mLayoutParams.gravity = Gravity.TOP
      mLayoutParams.setMargins(
          context.resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
          context.resources.getDimensionPixelSize(R.dimen.large_vertical_margin),
          context.resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin), 0)
      mSnackBarView.layoutParams = mLayoutParams
      if (listener == null) {
        mSnackBarView.setOnClickListener { mSnackBar.dismiss() }
      } else {
        mSnackBarView.setOnClickListener(listener)
      }
      mSnackBar.show()
    }
  }
}