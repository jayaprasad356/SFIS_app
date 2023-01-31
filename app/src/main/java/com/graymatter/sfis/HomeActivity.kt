package com.graymatter.sfis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rcdetail.setOnClickListener { startActivity(Intent(this,RCDetail::class.java)) }
      profilebtn.setOnClickListener { startActivity(Intent(this,ProfileActivity::class.java)) }
        share.setOnClickListener { startActivity(Intent(this,ReferralActivity::class.java)) }
    }
}