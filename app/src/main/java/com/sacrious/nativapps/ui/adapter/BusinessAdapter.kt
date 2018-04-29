package com.sacrious.nativapps.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.sacrious.nativapps.R
import com.sacrious.nativapps.model.Business

/**
 * Created by sacrious on 1/14/18.
 */
class BusinessAdapter(context: Context,
    private val mBusinessList: List<Business>) : BaseAdapter() {
  private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

  override fun getCount(): Int {
    return mBusinessList.size
  }

  override fun getItem(position: Int): Any {
    return mBusinessList[position]
  }

  override fun getItemId(position: Int): Long {
    return mBusinessList[position].id
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    var mConvertView = convertView
    if (mConvertView == null) {
      mConvertView = mLayoutInflater.inflate(R.layout.component_rounded, parent,
          false)
    }
    return getCustomView(position, mConvertView!!)
  }

  override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
    var mConvertView = convertView
    if (mConvertView == null) {
      mConvertView = mLayoutInflater.inflate(R.layout.layout_single_item, parent, false)
    }
    return getCustomView(position, mConvertView!!)
  }

  /**
   * This method set the information to show in UI for the dropdown view and the closed spinner
   * view (for easy implementation, the views are almost the same and have the same UI components
   * to render)
   *
   * @param position the position to show
   * @param convertView the custom view that going to who the data
   * @return the view to show
   */
  private fun getCustomView(position: Int, convertView: View): View {
    val name = convertView.findViewById(R.id.component_spinner_tv) as TextView
    name.text = mBusinessList[position].title
    return convertView
  }
}