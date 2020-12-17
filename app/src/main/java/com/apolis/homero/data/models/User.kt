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