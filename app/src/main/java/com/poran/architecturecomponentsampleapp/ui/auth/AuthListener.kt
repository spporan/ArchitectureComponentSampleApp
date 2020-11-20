package com.poran.architecturecomponentsampleapp.ui.auth

interface AuthListener {
    fun  onStarted()
    fun  onSuccess()
    fun  onFailure(msg:String)
}