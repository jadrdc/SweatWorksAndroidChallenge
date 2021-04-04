package com.example.sweatworksandroidinterviewtest.views.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sweatworksandroidinterviewtest.R
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.viewmodels.UserFavoriteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*


class UserDetailActivity : AppCompatActivity() {
    val model: UserFavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var users = UserFavorite(
            name = "Jose Agustin Reinoso",
            email = "Jadrdc@gmail.com",
            gender = "Male",
            lastname = "Reinoso",
            phone = "849-260-5023",
            picture = "https://randomuser.me/api/portraits/women/48.jpg"
        )
        setContentView(R.layout.activity_main)
        drawUI(users)



    }


    private fun drawUI(user: UserFavorite) {
        txtName?.text = user.name
        txtEmail?.text = user.email
        txtPhone?.text = user.phone
        Glide.with(this)
            .load(user.picture) // image url
            .into(profilePhoto) // resizing}
    }

    private fun showDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Aviso")
            .setMessage(message)
            .show()
    }
}