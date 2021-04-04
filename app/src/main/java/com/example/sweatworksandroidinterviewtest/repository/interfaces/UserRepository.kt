package com.example.sweatworksandroidinterviewtest.repository.interfaces

import com.example.sweatworksandroidinterviewtest.model.response.UserResponse
import io.reactivex.Observable

interface UserRepository {
    fun getUsers(page:Int): Observable<UserResponse>

}