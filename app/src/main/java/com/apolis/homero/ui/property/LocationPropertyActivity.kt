package com.apolis.homero.ui.property

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.apolis.homero.R
import com.apolis.homero.helpers.Constants
import com.apolis.homero.data.networks.GetAddressIntentService
import com.apolis.homero.helpers.d
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.helpers.toast
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_location_property.*


class LocationPropertyActivity : AppCompatActivity() {

    private val REQUEST_LOCATION_CODE = 1
    var fetchType: Int = Constants.USE_ADDRESS_NAME
    private var resultReceiver: AddressResultReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_property)

        init()
    }

    private fun init() {
        resultReceiver = AddressResultReceiver(null)
        onClick()
    }

    private fun onClick() {
        text_view_address.setOnClickListener {
            openActivity(this, PropertyActivity::class.java, null)
        }
        edit_text_where.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(view: TextView?, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                    keyCode == EditorInfo.IME_ACTION_DONE ||
                    event.action == KeyEvent.ACTION_DOWN &&
                    event.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    // Perform action on key press
                    checkLocationPermission()
                    sendResult()
                    return true
                }
                return false
            }
        })
    }

    private fun sendResult() {
        var intent = Intent(this, GetAddressIntentService::class.java)
        intent.putExtra(Constants.RECEIVER, resultReceiver)
        intent.putExtra(Constants.FETCH_TYPE_EXTRA, fetchType)
        if (fetchType == Constants.USE_ADDRESS_NAME) {
            if (edit_text_where.text.isEmpty()) {
                toast("Please enter an address name")
                return;
            }
            intent.putExtra(Constants.LOCATION_NAME_DATA_EXTRA, edit_text_where.text.toString());
        }
        d("Starting service")
        startService(intent)
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // we don't have permission so need to request
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_CODE
            )
        } else {
            // we already have permission we can start using
            getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_CODE && grantResults.isNotEmpty()) {
            getCurrentLocation()
        } else {
            toast("Permission denied")
        }
    }

    private fun getCurrentLocation() {
        var locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 3000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.getFusedLocationProviderClient(this)
            .lastLocation
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null) {
                    var lastLocation = it.result
                    text_view_details.text =
                        lastLocation.latitude.toString() + lastLocation.longitude.toString()
                }
            }
    }

    inner class AddressResultReceiver(handler: Handler?) : ResultReceiver(handler) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            if (resultCode == Constants.SUCCESS_RESULT) {
                val address: Address? = resultData!!.getParcelable(Constants.RESULT_ADDRESS)
                runOnUiThread {
                    var bundle = Bundle()
                    bundle.putString(Constants.RESULT_ADDRESS_EXTRA, address?.getAddressLine(0))
                    openActivity(this@LocationPropertyActivity, PropertyActivity::class.java, bundle)
                    text_view_details.text =
                        "Address: ${address?.getAddressLine(0)}"
                }
            }
        }
    }
}