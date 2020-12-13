package com.alan.githubsample.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alan.githubsample.R
import com.alan.githubsample.network.model.UserItems
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cell_github_user.view.*

/**
 * Created by AlanChang on 2020/12/10.
 */
class UsersAdapter : PagedListAdapter<UserItems, UsersAdapter.ViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_github_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        val size = super.getItemCount()
        return size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userAvatar = itemView.image_avatar
        private val userName = itemView.text_name

        fun bind(user: UserItems?) {
            userName.text = user?.login
            Glide.with(itemView.context)
                .load(user?.avatar_url)
                .placeholder(R.drawable.ic_placeholder_avatar)
                .circleCrop()
                .into(userAvatar)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserItems>() {
            override fun areItemsTheSame(oldItem: UserItems, newItem: UserItems): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItems, newItem: UserItems): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}