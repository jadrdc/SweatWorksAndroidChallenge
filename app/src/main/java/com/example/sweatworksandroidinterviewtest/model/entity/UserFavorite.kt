package com.example.sweatworksandroidinterviewtest.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserFavorite(
    @PrimaryKey var name: String,
    var lastname: String,
    var email: String?,
    var phone: String?,
    var gender: String?,
    var picture: String?
) {
}
