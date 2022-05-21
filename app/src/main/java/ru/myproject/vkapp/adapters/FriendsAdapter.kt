package ru.myproject.vkapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.myproject.vkapp.R
import ru.myproject.vkapp.databinding.CellFriendBinding
import ru.myproject.vkapp.models.FriendsModel

class FriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var sourceList: ArrayList<FriendsModel> = ArrayList()
    private var friendsList: ArrayList<FriendsModel> = ArrayList()

    fun setupFriends(friendsList: ArrayList<FriendsModel>) {
        sourceList.clear()
        sourceList.addAll(friendsList)
        filter("")

    }

    fun filter(query: String) {
        friendsList.clear()
        sourceList.forEach {
            if (it.name.contains(query, true) || it.surname.contains(query,true)) {
                friendsList.add(it)
            } else {
                it.city?.let { city ->
                    if (city.contains(query, ignoreCase = true)) {
                        friendsList.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        return FriendsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friendsList[position])
        }
    }

    override fun getItemCount(): Int {
        return friendsList.count()
    }


    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: CellFriendBinding = CellFriendBinding.bind(itemView)

        fun bind(friendsModel: FriendsModel) {

            Glide.with(itemView)
                .load(friendsModel.avatar)
                .placeholder(com.google.android.material.R.drawable.abc_dialog_material_background)
                .centerCrop()
                .into(binding.ivFriendsAvatar)
            binding.tvFriendsName.text = itemView.context.getString(
                R.string.name, friendsModel.name, friendsModel.surname
            )
            binding.tvFriendsCity.text =
                friendsModel.city ?: itemView.context.getString(R.string.city_null)
            if (friendsModel.isOnline) {
                binding.ivFriendsIsOnline.visibility = View.VISIBLE
            } else {
                binding.ivFriendsIsOnline.visibility = View.GONE
            }
        }
    }
}