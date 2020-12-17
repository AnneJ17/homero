package com.apolis.homero.app

class Endpoints {
    companion object {
        private val URL_LOGIN = "auth/login"
        private val URL_REGISTER = "auth/register"
        private val URL_USER = "users/"
        private val URL_PROPERTY = "/api/property/"
        private val URL_TENANT = "tenant/"
        private val URL_LANDLORD = "tenant/landlord/"
        private val URL_IMAGE_UPLOAD = "upload/property/picture"
    }

    fun getLogin(): String {
        return "${Config.BASE_URL + URL_LOGIN}"
    }

    fun getRegister(): String {
        return "${Config.BASE_URL + URL_REGISTER}"
    }

}