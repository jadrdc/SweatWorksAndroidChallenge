package com.example.sweatworksandroidinterviewtest.repository.impls

import android.content.Context
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.repository.interfaces.FavoriteUserRepository
import com.example.sweatworksandroidinterviewtest.source.local.UserRoomDatabase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class UserFavorityLocal(context: Context) : FavoriteUserRepository {

    private val database by lazy { UserRoomDatabase.getDatabase(context) }

    override fun getFavoriteUserList(): Single<List<UserFavorite>> {
        return database.userFavoriteDao().getFavoriteUsers()
    }

    override fun getUser(name: String): Maybe<UserFavorite?> {
        return  database.userFavoriteDao().userExists(name)
    }
    override fun saveUser(user: UserFavorite): Completable {
        return database.userFavoriteDao().saveFavoriteUser(user)
    }

    override fun deleteUser(user: UserFavorite): Completable {
        return database.userFavoriteDao().deleteUser(user)
    }
}