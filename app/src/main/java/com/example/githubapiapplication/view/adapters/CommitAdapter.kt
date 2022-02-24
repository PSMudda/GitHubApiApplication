package com.example.githubapiapplication.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapiapplication.databinding.CommitListItemBinding
import com.example.githubapiapplication.model.data.RepoCommits

class CommitAdapter : ListAdapter<RepoCommits, CommitAdapter.CommitViewHolder>(DiffCallback) {

    var commits = mutableListOf<RepoCommits>()

    fun setCommitList(commits: List<RepoCommits>) {
        this.commits = commits.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CommitViewHolder {
        return CommitViewHolder(CommitListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val commit = commits[position]

        holder.binding.author.text = commit.commits.author.name
        holder.binding.commitHash.text = commit.commits.tree.sha
        holder.binding.commitMessage.text = commit.commits.message
    }

    override fun getItemCount(): Int {
        return commits.size
    }

    class CommitViewHolder(
        var binding:
        CommitListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    companion object DiffCallback : DiffUtil.ItemCallback<RepoCommits>() {

        override fun areItemsTheSame(oldItem: RepoCommits, newItem: RepoCommits): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepoCommits, newItem: RepoCommits): Boolean {
            return oldItem == newItem
        }
    }
}