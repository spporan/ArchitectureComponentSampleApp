package com.poran.architecturecomponentsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import com.poran.architecturecomponentsampleapp.utils.ApiException
import com.poran.architecturecomponentsampleapp.utils.Coroutines
import com.poran.architecturecomponentsampleapp.utils.NoNetworkException

class LoginViewModel(
        private val repository: UserRepository
) :ViewModel() {
    var email:String?=null
    var password:String?=null
    var authListener:AuthListener?=null

    fun getLoggedInUser()= repository.getUser()


    fun onLoginButtonClick(view:View){
        authListener?.onStarted()
        if(email.isNullOrEmpty()||password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val response=repository.userLogin(email!!,password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)

            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)

            }
            catch (e:NoNetworkException){
                authListener?.onFailure(e.message!!)
            }


        }

    }

    fun onForgetPassword(view:View){
        authListener?.onForgot("forgot password")


    }
}