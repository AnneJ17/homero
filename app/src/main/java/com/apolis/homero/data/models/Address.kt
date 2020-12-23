package com.apolis.homero.data.models

import java.io.Serializable

data class Address(
    var address: String? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null
): Serializable {
    companion object {
        const val ADDRESS_KEY = "address_key"
    }
}