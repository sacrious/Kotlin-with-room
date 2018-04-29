package com.sacrious.nativapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.sacrious.nativapps.R
import com.sacrious.nativapps.common.utils.DateFormatterUtils
import com.sacrious.nativapps.model.Activity
import com.sacrious.nativapps.ui.adapter.ActivitiesListAdapter.ActivitiesViewHolder
import com.sacrious.nativapps.ui.common.extension.inflate
import kotlinx.android.synthetic.main.item_layout.view.description_txt
import kotlinx.android.synthetic.main.item_layout.view.subtitle_txt
import kotlinx.android.synthetic.main.item_layout.view.title_txt

/**
 * Created by sacrious on 1/14/18.
 */
class ActivitiesListAdapter(
    private val activities: List<Activity>) : RecyclerView.Adapter<ActivitiesViewHolder>() {
  override fun onBindViewHolder(holder: ActivitiesViewHolder?, position: Int) {
    holder?.bind(activities[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
    return ActivitiesViewHolder(parent.inflate(R.layout.item_layout))
  }

  override fun getItemCount(): Int = activities.size

  inner class ActivitiesViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(activity: Activity) {
      itemView.title_txt.text = activity.description
      itemView.subtitle_txt.text = activity.type
      itemView.description_txt.text = activity.date?.let { DateFormatterUtils.convertDateToString(it) }
    }
  }
}