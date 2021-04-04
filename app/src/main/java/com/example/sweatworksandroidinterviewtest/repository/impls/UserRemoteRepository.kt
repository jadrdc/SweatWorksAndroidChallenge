package com.example.sweatworksandroidinterviewtest.repository.impls

import com.example.sweatworksandroidinterviewtest.model.response.User
import com.example.sweatworksandroidinterviewtest.model.response.UserResponse
import com.example.sweatworksandroidinterviewtest.repository.interfaces.UserRepository
import com.example.sweatworksandroidinterviewtest.source.remote.RetrofitInstance
import com.example.sweatworksandroidinterviewtest.source.remote.service.UserService
import io.reactivex.Observable

object UserRemoteRepository : UserRepository {
    var userList = mutableListOf<User>()

    override fun getUsers(page: Int): Observable<UserResponse> {

        val request = RetrofitInstance.buildService(UserService::class.java)
        return request.getUsers(page)
    }

}