package com.example.sweatworksandroidinterviewtest.interactors

import android.content.Context
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.model.response.UserResponse
import com.example.sweatworksandroidinterviewtest.repository.impls.UserFavorityLocal
import com.example.sweatworksandroidinterviewtest.repository.impls.UserRemoteRepository
import com.example.sweatworksandroidinterviewtest.repository.interfaces.FavoriteUserRepository
import com.example.sweatworksandroidinterviewtest.repository.interfaces.UserRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserInteractor(context: Context) {
    private var repository: UserRepository = UserRemoteRepository
    private var localRepository: FavoriteUserRepository = UserFavorityLocal(context)

    fun getUsers(page: Int): Observable<UserResponse>? {
        return repository.getUsers(page).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getFavoriteUsers(): Single<List<UserFavorite>>? {
        return localRepository.getFavoriteUserList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteUser(userFavorite: UserFavorite): Completable {
        return localRepository.deleteUser(userFavorite).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUser(name:String): Maybe<UserFavorite?> {
        return localRepository.getUser(name).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun saveFavoriteUser(userFavorite: UserFavorite): Completable {
        return localRepository.saveUser(userFavorite).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}