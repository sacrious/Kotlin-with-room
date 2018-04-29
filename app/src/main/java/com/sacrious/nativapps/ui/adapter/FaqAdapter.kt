package com.sacrious.nativapps.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.sacrious.nativapps.R
import com.sacrious.nativapps.ui.common.extension.inflate
import kotlinx.android.synthetic.main.faq_item_layout.view.faq_txt_description_item
import kotlinx.android.synthetic.main.faq_item_layout.view.faq_txt_title_item
import kotlinx.android.synthetic.main.header_faq_layout.view.faq_txt_header

/**
 * Created by sacrious on 1/14/18.
 */
class FaqAdapter(private val mContext: Context) : RecyclerView.Adapter<ViewHolder>() {

  companion object {
    private const val HEADER = 0

    private const val BODY = 1
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    when (getItemViewType(position)) {
      HEADER -> (holder as FaqHeaderViewHolder).bind()
      BODY
      -> (holder as FaqViewHolder).bind(
          mContext.resources.getStringArray(R.array.faq_title)[position - 1],
          mContext.resources.getStringArray(R.array.faq_description)[position - 1])
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    when (viewType) {
      HEADER -> return FaqHeaderViewHolder(parent.inflate(R.layout.header_faq_layout))
      BODY
      -> return FaqViewHolder(parent.inflate(R.layout.faq_item_layout))
    }
    throw IllegalArgumentException("invalid view type")
  }

  override fun getItemCount(): Int {
    return mContext.resources.getStringArray(R.array.faq_title).size + 1
  }

  override fun getItemViewType(position: Int): Int {
    return if (position == HEADER) {
      HEADER
    } else {
      BODY
    }
  }

  inner class FaqViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(title: String?, description: String?) {
      itemView.faq_txt_title_item.text = title
      itemView.faq_txt_description_item.text = description
    }

  }

  inner class FaqHeaderViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind() {
      itemView.faq_txt_header.setText(R.string.faq)
    }

  }

}