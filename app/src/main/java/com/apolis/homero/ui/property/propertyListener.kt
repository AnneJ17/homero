package com.apolis.homero.ui.property

import androidx.lifecycle.MutableLiveData
import com.apolis.homero.data.models.PropertyData

interface propertyListener {
    fun onSave(response: MutableLiveData<PropertyData>)
    fun onCancel(messageString: String)
}