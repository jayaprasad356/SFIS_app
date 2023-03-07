package com.graymatter.sfis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.graymatter.sfis.helper.ApiConfig
import com.graymatter.sfis.helper.Constant
import kotlinx.android.synthetic.main.activity_rc_search.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RcSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rc_search)
        getInsuranceDetails(intent?.getStringExtra("bikeNumber").toString() ?: "")

        icBack.setOnClickListener {
            Intent(this,HomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun getInsuranceDetails(bikeNumber : String) {
        val params : HashMap<String,String> = hashMapOf()
        params.apply {
            this["vehicle_number"] =  bikeNumber
        }
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getString("status")!!.contentEquals("SUCCESS")) {
                        val `object` = JSONObject(response)
                        val jsonArray: JSONObject? = `object`.getJSONObject("vehicleDetails")
                        try {
                                jsonArray.let {
                                    bikeName.text = it!!.getString("manufacturer")
                                    bikeModel.text = it.getString("manufacturer_model") ?: ""
                                    validDate.text= it.getString("insurance_validity") ?: ""
                                    owner.text= it.getString("owner_name") ?: ""
                                    registrationNumber.text= it.getString("registration_number") ?: ""
                                    insuranceState.text= it.getString("rc_status") ?: ""
                                    DateOfRegistration.text= it.getString("registration_date") ?: ""
                                    policyNumber.text= it.getString("insurance_policy_no") ?: ""
                                    PolicyCompanyName.text= it.getString("insurance_company_name") ?: ""
                                    engineNumber.text= it.getString("engine_number") ?: ""
                                    fuelType.text= it.getString("fuel_type") ?: ""
                                    vehicelColor.text = it.getString("colour") ?: ""
                                    permanentAddress.text = it.getString("permanent_address") ?: ""
                                }
                        }catch (e:Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        Toast.makeText(
                            this@RcSearchActivity,
                            "" + jsonObject.getString(Constant.MESSAGE).toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("error",jsonObject.getString(Constant.MESSAGE).toString())
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this@RcSearchActivity, e.toString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.e("error",e.toString())

                }
            }
        }, this@RcSearchActivity, "https://sfis.graymatterworks.com/vehicle_details.php", params, true)
    }
}