package com.hotdogcode.aslamkhansbnri.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hotdogcode.aslamkhansbnri.R
import com.hotdogcode.aslamkhansbnri.data.model.api.User

class UserAdapter:PagedListAdapter<User,RecyclerView.ViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as UserViewHolder).bind(repoItem,position)
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}


class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    private val description:TextView = view.findViewById(R.id.description)
    private val openIssuesCount:TextView = view.findViewById(R.id.open_issues)
    private val l_key:TextView = view.findViewById(R.id.key)
    private val l_name:TextView = view.findViewById(R.id.l_name)
    private val spdx_id:TextView = view.findViewById(R.id.spdx_id)
    private val url:TextView = view.findViewById(R.id.url)
    private val nodeId:TextView = view.findViewById(R.id.node_id)
    private val admin:TextView = view.findViewById(R.id.admin)
    private val push:TextView = view.findViewById(R.id.push)
    private val pull:TextView = view.findViewById(R.id.pull)








    private var user: User? = null

    init {

    }

    fun bind(user: User?,position: Int) {
        if (user == null) {
            val resources = itemView.resources
        } else {
            showUserData(user,position )
        }
    }

    private fun showUserData(user: User,position: Int) {
        this.user = user
        name.text = user.name
        description.text = user.description
        openIssuesCount.text = user.openIssuesCount.toString()
        l_key.text = user.license?.key
        l_name.text = user.license?.name
        spdx_id.text = user.license?.spdxId
        url.text = user.license?.url
        nodeId.text = user.license?.nodeId
        admin.text = user.permissions?.admin.toString()
        push.text = user.permissions?.push.toString()
        pull.text = user.permissions?.pull.toString()
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row, parent, false)
            return UserViewHolder(view)
        }
    }
}