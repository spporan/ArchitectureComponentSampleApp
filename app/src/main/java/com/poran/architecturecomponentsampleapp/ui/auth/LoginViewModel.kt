package com.poran.architecturecomponentsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
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
            val response=UserRepository().userLogin(email!!,password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure(response.body()?.message!!)
            }

        }

    }

    fun onForgetPassword(view:View){
        authListener?.onForgot("forgot password")


    }
}