package com.graymatter.sfis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        update.setOnClickListener { startActivity(Intent(this,HomeActivity::class.java)) }
        backbtn.setOnClickListener { startActivity(Intent(this,HomeActivity::class.java)) }
    }
}