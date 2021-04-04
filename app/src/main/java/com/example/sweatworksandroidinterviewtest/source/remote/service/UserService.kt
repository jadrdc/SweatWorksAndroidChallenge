package com.example.sweatworksandroidinterviewtest.source.remote.service

import com.example.sweatworksandroidinterviewtest.model.response.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {


    @GET("?results=50&inc=gender,name,email,phone,cell,picture,login")
    fun getUsers(@Query("page") page: Int): Observable<UserResponse>

}
