package com.sacrious.nativapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.sacrious.nativapps.R
import com.sacrious.nativapps.model.Company
import com.sacrious.nativapps.ui.adapter.CompaniesListAdapter.CompaniesViewHolder
import com.sacrious.nativapps.ui.common.extension.inflate
import kotlinx.android.synthetic.main.item_layout.view.description_txt
import kotlinx.android.synthetic.main.item_layout.view.subtitle_txt
import kotlinx.android.synthetic.main.item_layout.view.title_txt

/**
 * Created by sacrious on 1/14/18.
 */
class CompaniesListAdapter(
    private val companies: List<Company>) : RecyclerView.Adapter<CompaniesViewHolder>() {
  override fun onBindViewHolder(holder: CompaniesViewHolder?, position: Int) {
    holder?.bind(companies[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesViewHolder {
    return CompaniesViewHolder(parent.inflate(R.layout.item_layout))
  }

  override fun getItemCount(): Int = companies.size

  inner class CompaniesViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(company: Company) {
      itemView.title_txt.text = company.name
      itemView.subtitle_txt.text = company.phone
      itemView.description_txt.text = company.address
    }
  }
}