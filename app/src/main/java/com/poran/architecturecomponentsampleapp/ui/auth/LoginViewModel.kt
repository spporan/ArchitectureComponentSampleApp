package com.poran.architecturecomponentsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import com.poran.architecturecomponentsampleapp.utils.ApiException
import com.poran.architecturecomponentsampleapp.utils.Coroutines

class LoginViewModel :ViewModel() {
    var email:String?=null
    var password:String?=null
    var authListener:AuthListener?=null;

    fun onLoginButtonClick(view:View){
        authListener?.onStarted()
        if(email.isNullOrEmpty()||password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val response=UserRepository().userLogin(email!!,password!!)
                response.user?.let {
                    authListener?.onSuccess(response.user)
                    return@main
                }
                authListener?.onFailure(response.message!!)

            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)

            }


        }

    }

    fun onForgetPassword(view:View){
        authListener?.onForgot("forgot password")


    }
}