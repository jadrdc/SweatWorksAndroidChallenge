package com.example.sweatworksandroidinterviewtest.utils

import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.model.response.User

object UserUtils {

fun castToUserEntity(userFetched :User):UserFavorite?
{
    var user= userFetched.name.last?.let { it1 -> userFetched.name.first?.let { it2 ->
        UserFavorite(name = it2,email = userFetched.email,lastname = it1,phone = userFetched.phone,gender = userFetched.gender,picture = userFetched.picture.large) } }
    return  user;
}
}