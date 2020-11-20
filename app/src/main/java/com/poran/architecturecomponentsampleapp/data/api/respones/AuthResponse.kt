package com.poran.architecturecomponentsampleapp.data.api.respones

import com.poran.architecturecomponentsampleapp.data.db.entities.User

data class AuthResponse (
    val isSuccessful:Boolean?,
    val message:String?,
    val user:User?
)