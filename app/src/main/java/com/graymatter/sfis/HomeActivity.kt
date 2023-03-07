package com.graymatter.sfis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_rc_search.*

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomSheetDialog : BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rcdetail.setOnClickListener { startActivity(Intent(this,RCDetail::class.java)) }
      profilebtn.setOnClickListener { startActivity(Intent(this,ProfileActivity::class.java)) }
        share.setOnClickListener { startActivity(Intent(this,ReferralActivity::class.java)) }
        RcSearch.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetDialog = BottomSheetDialog(this@HomeActivity).also {
            it.setContentView(R.layout.rc_search_bottom_sheet)

            val edBikeNumber = it.findViewById(R.id.edBikeNumber) as EditText?
            val btnSearch = it.findViewById(R.id.btnSearch) as Button?

            btnSearch?.setOnClickListener {
                if(edBikeNumber?.text.toString().isNotEmpty()) {
                    Intent(this,RcSearchActivity::class.java).also { intent->
                        intent.putExtra("bikeNumber",edBikeNumber!!.text.toString().trim())
                        startActivity(intent)
                    }
                }else{
                    edBikeNumber?.error = "Enter VehicleNumber"
                }
            }
        }
    }
}