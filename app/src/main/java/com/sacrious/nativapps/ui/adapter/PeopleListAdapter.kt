package com.sacrious.nativapps.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.sacrious.nativapps.R
import com.sacrious.nativapps.model.Person
import com.sacrious.nativapps.ui.adapter.PeopleListAdapter.PeopleViewHolder
import com.sacrious.nativapps.ui.common.extension.inflate
import kotlinx.android.synthetic.main.item_layout.view.description_txt
import kotlinx.android.synthetic.main.item_layout.view.subtitle_txt
import kotlinx.android.synthetic.main.item_layout.view.title_txt

/**
 * Created by sacrious on 1/14/18.
 */
class PeopleListAdapter(
    private val people: List<Person>) : RecyclerView.Adapter<PeopleViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
    return PeopleViewHolder(parent.inflate(R.layout.item_layout))
  }

  override fun onBindViewHolder(holder: PeopleViewHolder?, position: Int) {
    holder?.bind(people[position])
  }

  override fun getItemCount(): Int = people.size

  inner class PeopleViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(person: Person) {
      itemView.title_txt.text = person.name
      itemView.subtitle_txt.text = person.phone
      itemView.description_txt.text = person.email
    }
  }
}