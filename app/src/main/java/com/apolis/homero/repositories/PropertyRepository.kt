package com.apolis.homero.repositories

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import com.apolis.homero.data.models.PropertyData
import com.apolis.homero.data.models.PostPropertyResponse
import com.apolis.homero.data.networks.PropertyManagementApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyRepository {

    fun addProperty(address: String, city: String, country: String, image: String, mortgageInfo: Boolean, propertyStatus: Boolean, purchasePrice: String, state: String, userType: String): MutableLiveData<PropertyData> {
        var property = PropertyData(address = address, city = city, country = country, image = image, mortageInfo = mortgageInfo, propertyStatus = propertyStatus, purchasePrice = purchasePrice, state = state, userType = userType)
        val propertyResponse = MutableLiveData<PropertyData>()
        PropertyManagementApi().addProperty(property)
            .enqueue(object: Callback<PostPropertyResponse> {
                override fun onResponse(
                    call: Call<PostPropertyResponse>,
                    response: Response<PostPropertyResponse>
                ) {
                    if (response.isSuccessful) {
                        propertyResponse.value = response.body()?.data
                        // upload data to database
                    }
                }
                override fun onFailure(call: Call<PostPropertyResponse>, t: Throwable) {
                    d("PropertyViewModel: ", t.message.toString())
                }
            })
        return propertyResponse
    }

    fun addImage(url: String) {

    }
}