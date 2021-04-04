package com.example.sweatworksandroidinterviewtest.model.response

import com.example.sweatworksandroidinterviewtest.model.response.Login
import com.example.sweatworksandroidinterviewtest.model.response.Name
import com.example.sweatworksandroidinterviewtest.model.response.Picture
import java.io.Serializable

data class User(var  gender:String?, var name: Name, var email:String?, var login: Login?, var phone:String?, var cell:String?,
                var picture: Picture):Serializable{

}