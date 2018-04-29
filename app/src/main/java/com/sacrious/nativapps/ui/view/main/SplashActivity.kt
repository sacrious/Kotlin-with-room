package com.sacrious.nativapps.ui.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sacrious.nativapps.R
import android.content.Intent



class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }
}
