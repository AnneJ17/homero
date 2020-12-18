package com.apolis.homero.data.models

data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val __v: Int? = null,
    var _id: String? = null,
    val createdAt: String? = null,
    var email: String? = null,
    var name: String? = null,
    val password: String? = null,
    val type: String? = null
) {
    constructor(id: String, name: String, email: String) : this() {
        _id = id
        this.name = name
        this.email = email
    }
}

data class RegisterResponse(
    val data: Data,
    val error: Boolean,
    val message: String
)

data class Data(
    val __v: Int? = null,
    val _id: String? = null,
    val createdAt: String? = null,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    val type: String? = null
)