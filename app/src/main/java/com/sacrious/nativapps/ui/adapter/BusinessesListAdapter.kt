package com.sacrious.nativapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.sacrious.nativapps.R
import com.sacrious.nativapps.model.Business
import com.sacrious.nativapps.ui.adapter.BusinessesListAdapter.BusinessesViewHolder
import com.sacrious.nativapps.ui.common.extension.inflate
import kotlinx.android.synthetic.main.item_layout.view.description_txt
import kotlinx.android.synthetic.main.item_layout.view.subtitle_txt
import kotlinx.android.synthetic.main.item_layout.view.title_txt

/**
 * Created by sacrious on 1/14/18.
 */
class BusinessesListAdapter(
    private val business: List<Business>) : RecyclerView.Adapter<BusinessesViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessesViewHolder {
    return BusinessesViewHolder(parent.inflate(R.layout.item_layout))
  }

  override fun onBindViewHolder(holder: BusinessesViewHolder?, position: Int) {
    holder?.bind(business[position])
  }

  override fun getItemCount(): Int = business.size

  inner class BusinessesViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(business: Business) {
      itemView.title_txt.text = business.title
      itemView.subtitle_txt.text = business.description
      if (business.status) {
        itemView.description_txt.setText(R.string.enabled)
      } else {
        itemView.description_txt.setText(R.string.disabled)

      }
    }
  }
}