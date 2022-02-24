package com.example.githubapiapplication.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapiapplication.databinding.CommitListItemBinding
import com.example.githubapiapplication.model.data.Commit

class CommitAdapter() : ListAdapter<Commit, CommitAdapter.CommitViewHolder>(DiffCallback) {
    //Test data
    var commits = mutableListOf(Commit("1","Pritam Mudda","1234abcd5678ahsjhfjdkjdhjdjsdjhksjhksjhdfkshfd","Initial commit"),
        Commit("2","Pritam Mudda","1234abcd5678ahsjhfjdkjdhjdjsdjhksjhksjhdfkshfd","Initial commit"),
        Commit("3","Pritam Mudda","1234abcd5678ahsjhfjdkjdhjdjsdjhksjhksjhdfkshfd","Initial commit"),
        Commit("4","Pritam Mudda","1234abcd5678ahsjhfjdkjdhjdjsdjhksjhksjhdfkshfd","Initial commit")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        return CommitViewHolder(CommitListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val commit = commits[position]
        holder.binding.author.text = commit.author
        holder.binding.commitHash.text = commit.sha
        holder.binding.commitMessage.text = commit.message
    }

    override fun getItemCount(): Int {
        return commits.size
    }

    class CommitViewHolder(
        var binding: CommitListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Commit>() {

        override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
            return oldItem == newItem
        }
    }
}