package com.example.sweatworksandroidinterviewtest.views.screens

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweatworksandroidinterviewtest.R
import com.example.sweatworksandroidinterviewtest.adapters.SavedFavoriteUserAdapter
import com.example.sweatworksandroidinterviewtest.adapters.UserGridAdapter
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.model.response.User
import com.example.sweatworksandroidinterviewtest.viewmodels.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_user_contact_list.*

class UserContactList : AppCompatActivity(), SavedFavoriteUserAdapter.OnClickSavedFavoriteUser {

    private var adapter: UserGridAdapter? = null
    val model: UserViewModel by viewModels()
    var PAGEINDEX = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_contact_list)

        model.userFavoriteList.observe(this, Observer { it ->
            if (it.isNotEmpty()) {
                drawSavedFavoriteList(it)

            }
        })

        model.userList.observe(this, Observer {
            drawUserList(it)
        })
        model.searchList.observe(this, Observer {
            setSearchListAdapter(it)
        })

        searchBar.onItemClickListener = object : AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var name = searchBar.text.toString()
                var user = adapter?.getUser(word = name)
                user.let {
                    val mainIntent = Intent(applicationContext, UserDetailActivity::class.java)
                    mainIntent.putExtra("USER", it)
                    startActivity(mainIntent)

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

        }
        addContact?.setOnClickListener {
            if (searchBar.length() > 0) {

                val intent = Intent(ContactsContract.Intents.Insert.ACTION)
                intent.type = ContactsContract.RawContacts.CONTENT_TYPE
                var name = searchBar.text.toString()
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
                startActivity(intent)
            }
        }

        model.getUserList(PAGEINDEX)
    }

    override fun onResume() {
        super.onResume()
        model.getUserFavoriteList()


    }

    private fun drawUserList(list: List<User>) {
        adapter = UserGridAdapter(list = list.toMutableList(), context = this)
        grdUserList.adapter = adapter
    }

    private fun setSearchListAdapter(it: List<String>) {
        val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, android.R.layout.select_dialog_item, it)
        searchBar.threshold = 2
        searchBar.setAdapter(adapter)
    }


    private fun drawSavedFavoriteList(favoriteList: List<UserFavorite>) {
        txtFavoriteUser.visibility = View.VISIBLE
        var adapter = SavedFavoriteUserAdapter(list = favoriteList.toMutableList(), listener = this)
        rcFavoriteUserList.visibility = View.VISIBLE
        val linearLayoutManager: LinearLayoutManager? =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rcFavoriteUserList?.layoutManager = linearLayoutManager
        this.rcFavoriteUserList.adapter = adapter

    }

    override fun delete(user: UserFavorite) {

        MaterialAlertDialogBuilder(this)
                .setTitle("Aviso")
                .setMessage("Deseas eliminar este usuario de favoritos?")
                .setPositiveButton("Si") { dialog, which ->
                    model.deleteUser(user)
                }.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
    }

}