package com.poran.architecturecomponentsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import com.poran.architecturecomponentsampleapp.utils.ApiException
import com.poran.architecturecomponentsampleapp.utils.Coroutines
import com.poran.architecturecomponentsampleapp.utils.NoNetworkException
import com.poran.architecturecomponentsampleapp.utils.toast

class AuthViewModel(
        private val repository: UserRepository
) :ViewModel() {
    var email:String?=null
    var password:String?=null
    var passwordConfirm:String?=null
    var name:String?=null
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

    fun onSignUpButtonClick(view:View){
        authListener?.onStarted()
        if(name.isNullOrEmpty()){
            authListener?.onFailure("Required Name")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailure("Required email")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailure("Required email")
            return
        }
        if(password !=passwordConfirm){
            authListener?.onFailure("Password didn't match")
            return
        }

        Coroutines.main {
            try {
                val response=repository.userSignUp(name!!,email!!,password!!)
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

    fun gotoSignUp(view:View){
        view.context.toast("Goto sign up")
        Intent(view.context,SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }

    }
    fun gotoSignIn(view:View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }

    }
}