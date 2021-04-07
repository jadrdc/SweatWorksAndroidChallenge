package com.example.sweatworksandroidinterviewtest.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserFavoriteDAO {

    @Query("SELECT * FROM users")
    fun getFavoriteUsers(): Single<List<UserFavorite>>

    @Query("SELECT * FROM users where name= :name")
    fun userExists(name:String):Maybe<UserFavorite?>

    @Insert
    fun saveFavoriteUser(userFavorite: UserFavorite): Completable

    @Delete
    fun deleteUser(userFavorite: UserFavorite): Completable
}

