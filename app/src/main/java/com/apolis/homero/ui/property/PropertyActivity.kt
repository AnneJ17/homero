package com.apolis.homero.ui.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.apolis.homero.R
import com.apolis.homero.data.models.Address
import com.apolis.homero.data.models.PropertyData
import com.apolis.homero.helpers.Constants
import com.apolis.homero.helpers.d
import com.apolis.homero.helpers.toast
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.io.Serializable

class PropertyActivity : AppCompatActivity(), propertyListener, AddPropertyFragment.OnFragmentInteraction {

    var propertyAddress: Address? = null
    var propertyExtra: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        init()
    }

    private fun init() {
        setUpToolbar()
        setUpActivity()
        parseData()
        connect()
//        sendData()
    }

    private fun sendData() {
        var transaction = supportFragmentManager.beginTransaction()
        var bundle = Bundle()
        bundle.putSerializable(Address.ADDRESS_KEY, propertyAddress as Serializable)
        d(propertyAddress.toString())
        var fragInfo = AddPropertyFragment()
        fragInfo.arguments = bundle
        transaction.replace(R.id.view_pager, fragInfo)
        transaction.commit()
    }

    private fun setUpActivity() {
        view_pager.adapter = FragmentAdapter(supportFragmentManager)
    }

    private fun connect() {
        var propertyViewModel = ViewModelProviders.of(this).get(PropertyViewModel::class.java)
        propertyViewModel.propertyListener = this
    }

    private fun parseData() {
        propertyExtra = intent.extras?.getString(Constants.RESULT_ADDRESS_EXTRA)
        d("Property: $propertyExtra")
        var strArray = propertyExtra?.split(",")?.toTypedArray()
        when(strArray?.size) {
            4 -> {
                var line = strArray[0].trim()
                var city = strArray[1].trim()
                var stateZipcode = strArray[2].split(" ")
                var state = stateZipcode[0].trim()
                var country = strArray[3].trim()
                propertyAddress = Address(address = line, city = city, state = state, country = country)
                var addPropertyFragment = AddPropertyFragment.newInstance(country, line, state, city)
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, addPropertyFragment)
                    .commit()
            }
            else -> {
                toast("please enter first line of your address")
            }
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
//            view_pager.setCurrentItem(0)
//            R.id.menu_save -> openActivity(this, PropertyDetailActivity::class.java, null)
        }
        return true
    }

    override fun onSave(response: MutableLiveData<PropertyData>) {

    }

    override fun onCancel(messageString: String) {

    }

    override fun onNextClicked() {
        view_pager.currentItem = 2
    }
}