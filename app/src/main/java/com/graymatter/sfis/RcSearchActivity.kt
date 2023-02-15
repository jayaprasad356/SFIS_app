package com.graymatter.sfis

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
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
    }

    private fun getInsuranceDetails() {
        val params : HashMap<String,String> = hashMapOf()
        params.apply {

        }
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val `object` = JSONObject(response)
                        val jsonArray: JSONArray? = `object`.getJSONArray("vehicleDetails")
                        try {
                            jsonArray.let {
                                bikeName.text = jsonArray?.getString(19) ?: ""
                                bikeModel.text = jsonArray?.getString(20) ?: ""
                                validDate.text= jsonArray?.getString(24) ?: ""
                                owner.text= jsonArray?.getString(4) ?: ""
                                bikeNumber.text= jsonArray?.getString(0) ?: ""
                                insuranceState.text= jsonArray?.getString(1) ?: ""
                                DateOfRegistration.text= jsonArray?.getString(6) ?: ""
                                    policyNumber.text= jsonArray?.getString(22) ?: ""
                                PolicyCompanyName.text= jsonArray?.getString(23) ?: ""
                                engineNumber.text= jsonArray?.getString(3) ?: ""
                                fuelType.text= jsonArray?.getString(7) ?: ""
                                vehicelColor.text = jsonArray?.getString(18) ?: ""
                                permanentAddress.text = jsonArray?.getString(29) ?: ""
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
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this@RcSearchActivity, e.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }, this@RcSearchActivity, "https://gmw.graymatterworks.com/vehicle_details.php", params, true)
    }
}