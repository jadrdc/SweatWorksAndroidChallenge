package com.example.sweatworksandroidinterviewtest.repository.interfaces

import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface FavoriteUserRepository {
    fun getFavoriteUserList(): Single<List<UserFavorite>>
    fun saveUser(user: UserFavorite): Completable
    fun deleteUser(user: UserFavorite): Completable
    fun getUser(name:String):Maybe<UserFavorite?>

}