package com.thangnguyen.demomvvm.view.repo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thangnguyen.demomvvm.data.db.entity.Repo
import com.thangnguyen.demomvvm.databinding.ItemRepoBinding


class RepoAdapter(private val onClickListener: (Repo) -> Unit) :
    PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(CharacterComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { repo ->
            holder.binding.also {
                it.repo = repo
                it.setOnClick { onClickListener(repo) }
                it.executePendingBindings()
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem == newItem
    }

    class ViewHolder(
        val binding: ItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root)
}