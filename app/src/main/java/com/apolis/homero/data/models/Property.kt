package com.apolis.homero.data.models

data class GetPropertyResponse(
    val data: ArrayList<PropertyData>,
    val error: Boolean
)

data class PostPropertyResponse(
    val data: PropertyData,
    val error: Boolean,
    val message: String
)

data class PropertyData(
    val _id: String? = null,
    val address: String? = null,
    val city: String? = null,
    val country: String? = null,
    val image: String? = null,
    val mortageInfo: Boolean? = null,
    val propertyStatus: Boolean? = null,
    val purchasePrice: String? = null,
    val state: String? = null,
    val userId: String? = null,
    val userType: String? = null
)