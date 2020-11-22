package com.poran.architecturecomponentsampleapp.data.api

import com.poran.architecturecomponentsampleapp.data.api.respones.AuthResponse
import com.poran.architecturecomponentsampleapp.data.api.respones.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST(value = "login")
  suspend  fun userLogin(
       @Field("email") email:String,
       @Field("password") password:String
    ):Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password: String
    ):Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes():Response<QuoteResponse>

    companion object{
         operator fun invoke(
               interceptor: NetworkConnectionInterceptor
         ):ApiService{
             val okHttpClient=OkHttpClient
                 .Builder()
                 .addInterceptor(interceptor)
                 .build()

            return Retrofit
                .Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}

