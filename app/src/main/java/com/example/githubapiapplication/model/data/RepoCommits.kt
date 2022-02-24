package com.example.githubapiapplication.model.data

import com.google.gson.annotations.SerializedName

data class RepoCommits(@SerializedName("commit")val commits: RepoCommit, @SerializedName("sha")val id:String)
