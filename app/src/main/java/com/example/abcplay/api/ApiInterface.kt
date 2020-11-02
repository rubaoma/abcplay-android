package com.example.abcplay.api

import com.example.abcplay.model.SignInBody
import com.example.abcplay.model.Token
import com.example.abcplay.model.UserBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("Content-Type:application/json")
    @POST("login")
    fun signin(@Body info: SignInBody): retrofit2.Call<ResponseBody>


    @Headers("Content-Type:application/json")
    @POST("register")
    fun registerUser(@Body info: UserBody): retrofit2.Call<ResponseBody>

}

