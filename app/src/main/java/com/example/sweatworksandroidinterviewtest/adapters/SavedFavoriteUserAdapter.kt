package com.example.sweatworksandroidinterviewtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweatworksandroidinterviewtest.R
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import kotlinx.android.synthetic.main.userlayout.view.*

class SavedFavoriteUserAdapter(var list: MutableList<UserFavorite>, var listener: OnClickSavedFavoriteUser) : RecyclerView.Adapter<SavedFavoriteUserAdapter.SavedFavoriteViewholder>() {

    interface OnClickSavedFavoriteUser {
        fun delete(user: UserFavorite)
    }

    class SavedFavoriteViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun drawUI(userFavorite: UserFavorite, listener: OnClickSavedFavoriteUser) {
            Glide.with(itemView.context).load(userFavorite.picture).into(itemView.profilePhoto)
            itemView.profilePhoto?.setOnClickListener {
                listener.delete(userFavorite)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedFavoriteViewholder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.userlayout, parent, false)
        return SavedFavoriteViewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SavedFavoriteViewholder, position: Int) {
        holder.drawUI(list[position],listener)
    }
}