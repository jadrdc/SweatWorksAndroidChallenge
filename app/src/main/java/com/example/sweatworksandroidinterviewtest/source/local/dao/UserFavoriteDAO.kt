package com.example.sweatworksandroidinterviewtest.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import io.reactivex.*

@Dao
interface UserFavoriteDAO {

    @Query("SELECT * FROM users")
    fun getFavoriteUsers(): Single<List<UserFavorite>>

    @Insert
    fun saveFavoriteUser(userFavorite: UserFavorite):Completable

}

