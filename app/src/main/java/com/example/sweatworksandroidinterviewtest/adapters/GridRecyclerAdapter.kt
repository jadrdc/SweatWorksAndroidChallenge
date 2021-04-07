package com.example.sweatworksandroidinterviewtest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweatworksandroidinterviewtest.R
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.model.response.User
import com.example.sweatworksandroidinterviewtest.utils.UserUtils
import com.example.sweatworksandroidinterviewtest.views.screens.UserDetailActivity
import kotlinx.android.synthetic.main.userlayout.view.*

class GridRecyclerAdapter(var list: MutableList<User>, val context: Context) :
    RecyclerView.Adapter<GridRecyclerAdapter.UserGridViewHolder>() {


    fun addAll(newList: MutableList<User>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridRecyclerAdapter.UserGridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.userlayout, parent, false)
        return UserGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserGridViewHolder, position: Int) {

        holder.draw(list[position])
    }

    fun getUser(word: String): UserFavorite? {
        var user = list.first {
            it.name.first == word || it.name.last == word
        }
        return UserUtils.castToUserEntity(user)
    }


    class UserGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun draw(user: User) {
            Glide.with(itemView.context)
                .load(user.picture.thumbnail) // image url
                .placeholder(R.drawable.ic_name_background) // any placeholder to load at start
                .error(R.drawable.ic_name_foreground)  // any image in case of error
                .override(200, 200).into(itemView.profilePhoto) // resizing

            itemView.profilePhoto.setOnClickListener {
                val mainIntent = Intent(itemView.context, UserDetailActivity::class.java)

                var userEntity = UserUtils.castToUserEntity(user)
                mainIntent.putExtra("USER", userEntity)
                itemView.context.startActivity(mainIntent)
            }
        }
    }
}