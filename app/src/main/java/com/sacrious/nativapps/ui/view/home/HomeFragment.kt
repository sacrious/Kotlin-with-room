package com.sacrious.nativapps.ui.view.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sacrious.nativapps.R
import com.sacrious.nativapps.common.di.DiComponent
import com.sacrious.nativapps.ui.adapter.FaqAdapter
import com.sacrious.nativapps.ui.mvp.home.HomePresenter
import com.sacrious.nativapps.ui.mvp.home.HomeView
import com.sacrious.nativapps.ui.view.common.BaseFragment
import com.sacrious.nativapps.ui.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.faq_rv

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<HomePresenter>(), HomeView {
  override fun onAdd() {

  }

  override fun onShowList() {

  }

  override fun buildPresenter(): HomePresenter = HomePresenter(this)

  override fun injectComponent(diComponent: DiComponent) {
    diComponent.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    faq_rv.layoutManager = LinearLayoutManager(context)
    faq_rv.adapter = context?.let { FaqAdapter(it) }

    (activity as MainActivity).showButtons(false)
  }
}
