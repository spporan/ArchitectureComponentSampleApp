package com.poran.architecturecomponentsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.poran.architecturecomponentsampleapp.data.db.entities.User

interface AuthListener {
    fun  onStarted()
    fun  onSuccess(user: User)
    fun  onFailure(msg:String)
    fun onForgot(msg: String)
}