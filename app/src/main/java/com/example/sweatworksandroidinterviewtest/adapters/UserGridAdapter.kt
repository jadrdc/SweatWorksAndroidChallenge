package com.example.sweatworksandroidinterviewtest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.sweatworksandroidinterviewtest.R
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.model.response.User
import com.example.sweatworksandroidinterviewtest.utils.UserUtils
import com.example.sweatworksandroidinterviewtest.views.screens.UserDetailActivity
import kotlinx.android.synthetic.main.userlayout.view.*

class UserGridAdapter(var list: MutableList<User>, val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    fun addElementsList(listElements: MutableList<User>) {
        list.addAll(listElements)
        notifyDataSetChanged()
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var root: View
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            root = View(context)
            root = inflater.inflate(R.layout.userlayout, null)

            var userFetched = list[position]
            Glide.with(context)
                    .load(userFetched.picture.thumbnail) // image url
                    .placeholder(R.drawable.ic_name_background) // any placeholder to load at start
                    .error(R.drawable.ic_name_foreground)  // any image in case of error
                    .override(200, 200).into(root.profilePhoto) // resizing

            root.profilePhoto.setOnClickListener {
                val mainIntent = Intent(context, UserDetailActivity::class.java)

                var user = UserUtils.castToUserEntity(userFetched)
                mainIntent.putExtra("USER", user)
                context.startActivity(mainIntent)
            }

        } else {
            root = convertView
        }
        return root
    }

    fun getUser(word: String): UserFavorite? {
        var user = list.first {
            it.name.first == word || it.name.last == word
        }
        return UserUtils.castToUserEntity(user)
    }

    override fun getItem(position: Int): User {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 1
    }

}