package com.poran.architecturecomponentsampleapp.data.api

import com.poran.architecturecomponentsampleapp.utils.ApiException
import com.poran.architecturecomponentsampleapp.utils.NoNetworkException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {
    suspend fun<T:Any> apiRequest(call:suspend ()->Response<T>):T{
        val  response=call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val errorMsg=StringBuilder()
            val error=response.errorBody().toString()
            error.let {
                try {
                    errorMsg.append(JSONObject(it).getString("message"))
                }catch (e:JSONException){}
                catch (e:NoNetworkException){

                }
                finally {
                    errorMsg.append("\n")
                    errorMsg.append("Error code ${response.code()}")
                }

                throw ApiException(errorMsg.toString())
            }
        }

    }
}