package com.apolis.homero.ui.property

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apolis.homero.data.models.PropertyData
import com.apolis.homero.repositories.PropertyRepository

class PropertyViewModel : ViewModel() {
    var propertyListener: propertyListener? = null
    private var propertyLiveData: MutableLiveData<PropertyData> = MutableLiveData()

    var country: String = ""
    var line1: String = ""
    var city: String = ""
    var state: String = ""
    var image: String = ""
    var mortgageInfo: Boolean? = null
    var propertyStatus: Boolean? = null
    var purchasePrice: String = ""
    var userType: String = ""

    fun onSaveButtonClicked() {
        //get data from activity
        propertyLiveData = PropertyRepository().addProperty(line1, city, country, image, mortgageInfo!!, propertyStatus!!, purchasePrice, state, userType)
        propertyListener?.onSave(propertyLiveData)
    }

    fun onCancelButtonClicked() {
        propertyListener?.onCancel("Upload failed")
    }
}