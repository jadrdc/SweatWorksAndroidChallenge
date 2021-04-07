package com.example.sweatworksandroidinterviewtest.views.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.sweatworksandroidinterviewtest.R
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.viewmodels.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*


class UserDetailActivity : AppCompatActivity() {
    val model: UserViewModel by viewModels()
    var user: UserFavorite? = null
    val USER = "USER"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = intent.extras?.get(USER) as UserFavorite?
        setContentView(R.layout.activity_main)
        user?.let { drawUI(it) }

        model.isSavingUser.observe(this, Observer {
            var msg = "Usuario ha sido agregado a favoritos"
            if (!it)
                showDialog(message = msg)
        })

        model.isDuplicatedUser.observe(this, Observer {
            var msg = "Usuario ya se habia agregado a favoritos"
            if (it)
                showDialog(message = msg)
        })


        model.isDeletedUser.observe(this, Observer {
            var msg = "Usuario ha sido eliminado de favoritos"

            if (!it)
                showDialog(message = msg)
        })
        isFavorite?.setOnRatingBarChangeListener { _, rating, _ ->
            if (rating < 1f) {
                txtIsFavorite?.text = "No es un favorito"
                user?.let {
                    model.deleteUser(userFavorite = it)
                    model.setIsDeleteUser()
                }

            } else {
                txtIsFavorite?.text = "Es un favorito"
                user?.let { model.saveFavoriteUser(it) }
            }
        }
    }


    private fun drawUI(user: UserFavorite) {
        txtName?.text = user.name.plus(" ").plus(user.lastname)
        txtEmail?.text = user.email
        txtPhone?.text = user.phone
        Glide.with(this).load(user.picture).into(profilePhoto)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDialog(message: String) {
        MaterialAlertDialogBuilder(this)
                .setTitle("Aviso")
                .setMessage(message)
                .show()
    }
}