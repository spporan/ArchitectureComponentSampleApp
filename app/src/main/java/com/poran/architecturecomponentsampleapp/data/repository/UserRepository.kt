package com.poran.architecturecomponentsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.api.SafeApiRequest
import com.poran.architecturecomponentsampleapp.data.api.respones.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class UserRepository : SafeApiRequest(){

  suspend  fun userLogin(email:String,password:String):AuthResponse{
      return  apiRequest { ApiService().userLogin(email,password) }

    }
}