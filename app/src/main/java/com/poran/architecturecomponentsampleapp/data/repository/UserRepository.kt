package com.poran.architecturecomponentsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.api.respones.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class UserRepository {

  suspend  fun userLogin(email:String,password:String):Response<AuthResponse>{
      return  ApiService().userLogin(email,password)

    }
}