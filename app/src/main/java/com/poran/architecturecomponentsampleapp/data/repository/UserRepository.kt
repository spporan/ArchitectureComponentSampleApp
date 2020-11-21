package com.poran.architecturecomponentsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.api.SafeApiRequest
import com.poran.architecturecomponentsampleapp.data.api.respones.AuthResponse
import com.poran.architecturecomponentsampleapp.data.db.AppDatabase
import com.poran.architecturecomponentsampleapp.data.db.entities.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class UserRepository (
        private val apiService:ApiService,
        private val appDatabase: AppDatabase
): SafeApiRequest(){

  suspend  fun userLogin(email:String,password:String):AuthResponse{
      return  apiRequest { ApiService().userLogin(email,password) }

    }

    suspend fun saveUser(user:User){
        appDatabase.userDao().insert(user)
    }
    fun getUser()=appDatabase.userDao().getUSer()
}