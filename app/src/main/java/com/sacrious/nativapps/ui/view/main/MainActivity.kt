package com.sacrious.nativapps.ui.view.main

import android.animation.Animator
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.sacrious.nativapps.R
import com.sacrious.nativapps.ui.common.extension.hide
import com.sacrious.nativapps.ui.common.extension.show
import com.sacrious.nativapps.ui.mvp.main.MainPresenter
import com.sacrious.nativapps.ui.mvp.main.MainView
import com.sacrious.nativapps.ui.view.activity.ActivityFragment
import com.sacrious.nativapps.ui.view.business.BusinessFragment
import com.sacrious.nativapps.ui.view.common.BaseActivity
import com.sacrious.nativapps.ui.view.common.BaseFragment
import com.sacrious.nativapps.ui.view.common.FragmentCallback
import com.sacrious.nativapps.ui.view.company.CompanyFragment
import com.sacrious.nativapps.ui.view.home.HomeFragment
import com.sacrious.nativapps.ui.view.person.PersonFragment
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.app_bar_main.animation_view
import kotlinx.android.synthetic.main.app_bar_main.fab_list
import kotlinx.android.synthetic.main.app_bar_main.fab_plus
import kotlinx.android.synthetic.main.app_bar_main.fab_save
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import kotlinx.android.synthetic.main.bottom_sheet.bottom_sheet
import kotlinx.android.synthetic.main.bottom_sheet.bottom_sheet_rv
import kotlinx.android.synthetic.main.bottom_sheet.bottom_sheet_txt


class MainActivity : BaseActivity<MainPresenter>(), NavigationView.OnNavigationItemSelectedListener, MainView, View.OnClickListener {

  private var fragmentCallback: FragmentCallback? = null
  private var fabOpen: Animation? = null
  private var fabClose: Animation? = null
  private var fabRotClockwise: Animation? = null
  private var fabRotAnticlockwise: Animation? = null
  private var isFabOpened = false

  var sheetBehavior: BottomSheetBehavior<*>? = null

  override fun buildPresenter(): MainPresenter = MainPresenter(
      this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close)
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()
    nav_view.setNavigationItemSelectedListener(this)

    this.fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
    this.fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
    this.fabRotClockwise = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_clockwise)
    this.fabRotAnticlockwise = AnimationUtils.loadAnimation(applicationContext,
        R.anim.rotate_anticlockwise)

    fab_plus.setOnClickListener(this)
    fab_save.setOnClickListener(this)
    fab_list.setOnClickListener(this)

    setNavigationSelectedItem(R.id.nav_home, HomeFragment())
    fab_plus.hide(false)

    winkAnimation()
    bottomSheet()

  }

  private fun bottomSheet() {
    sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
    sheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
      override fun onStateChanged(bottomSheet: View, newState: Int) {
        when (newState) {
          BottomSheetBehavior.STATE_EXPANDED -> {
          }
        }
      }

      override fun onSlide(bottomSheet: View, slideOffset: Float) {

      }
    })
  }

  fun renderAdapter(adapter: RecyclerView.Adapter<*>) {
    bottom_sheet_txt.text = getString(R.string.item_number, adapter.itemCount)
    bottom_sheet_rv.layoutManager = LinearLayoutManager(this,
        LinearLayoutManager.HORIZONTAL, false)
    bottom_sheet_rv.adapter = adapter
  }

  private fun winkAnimation() {
    animation_view.addAnimatorListener(object : Animator.AnimatorListener {
      override fun onAnimationStart(animator: Animator) {

      }

      override fun onAnimationEnd(animator: Animator) {
        animation_view.cancelAnimation()
        animation_view.animate().translationY(animation_view.height.toFloat()).alpha(0.0f)
      }

      override fun onAnimationCancel(animator: Animator) {

      }

      override fun onAnimationRepeat(animator: Animator) {

      }
    })

    animation_view.animate().alpha(1.0f).translationY(0F)
    animation_view.playAnimation()
  }

  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun addedSuccessfully(name: String) {}

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_person -> {
        fragmentReplacement(PersonFragment())
        toolbar.setTitle(R.string.people)
        fab_plus.show()
      }
      R.id.nav_company -> {
        fragmentReplacement(CompanyFragment())
        toolbar.setTitle(R.string.companies)
        fab_plus.show()
      }
      R.id.nav_business -> {
        fragmentReplacement(BusinessFragment())
        toolbar.setTitle(R.string.businesses)
        fab_plus.show()
      }
      R.id.nav_activity -> {
        fragmentReplacement(ActivityFragment())
        toolbar.setTitle(R.string.activities)
        fab_plus.show()
      }
      R.id.nav_home -> {
        fragmentReplacement(HomeFragment())
        toolbar.setTitle(R.string.home)
        fab_plus.hide(false)
      }
    }
    sheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    if (isFabOpened) {
      fab_plus.performClick()
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }

  fun setNavigationSelectedItem(id: Int,
      fragment: BaseFragment<*>) {
    nav_view.setCheckedItem(id)
    fragmentReplacement(fragment)
  }

  private fun fragmentReplacement(fragment: BaseFragment<*>) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.fragment_container, fragment)
    transaction.commitAllowingStateLoss()
    fragmentCallback = fragment.getFragmentCallback()
  }

  override fun onClick(view: View?) {
    if (view?.id == R.id.fab_plus) {
      if (view.visibility != View.INVISIBLE) {
        if (isFabOpened) {
          fab_save.startAnimation(fabClose)
          fab_list.startAnimation(fabClose)
          fab_plus.startAnimation(fabRotAnticlockwise)
          fab_save.isClickable = false
          fab_list.isClickable = false
          isFabOpened = false
        } else {
          fab_save.startAnimation(fabOpen)
          fab_list.startAnimation(fabOpen)
          fab_plus.startAnimation(fabRotClockwise)
          fab_save.isClickable = true
          fab_list.isClickable = true
          isFabOpened = true
        }
      }
    } else if (view?.id == R.id.fab_save) {
      fab_plus.performClick()
      fragmentCallback?.onAdd()
    } else if (view?.id == R.id.fab_list) {
      fab_plus.performClick()
      fragmentCallback?.onShowList()
      if (sheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED) {
        sheetBehavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
      } else {
        sheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
      }
    }
  }

  fun showButtons(show: Boolean) {
    if (show) {
      fab_plus.show()
      bottom_sheet.show()
    } else {
      fab_plus.hide(false)
      bottom_sheet.hide(false)
    }
  }
}
