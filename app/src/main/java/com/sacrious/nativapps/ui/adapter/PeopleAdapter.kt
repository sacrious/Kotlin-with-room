package com.sacrious.nativapps.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.sacrious.nativapps.R
import com.sacrious.nativapps.model.Person

/**
 * Created by sacrious on 1/13/18.
 */
class PeopleAdapter(context: Context,
    private val mPeopleList: List<Person>) : BaseAdapter() {
  private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

  override fun getCount(): Int {
    return mPeopleList.size
  }

  override fun getItem(position: Int): Any {
    return mPeopleList[position]
  }

  override fun getItemId(position: Int): Long {
    return mPeopleList[position].id
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
    name.text = mPeopleList[position].name
    return convertView
  }
}