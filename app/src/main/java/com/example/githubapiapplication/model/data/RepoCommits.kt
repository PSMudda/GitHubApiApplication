package com.example.githubapiapplication.model.data

import com.google.gson.annotations.SerializedName

data class RepoCommits(@SerializedName("commit")val commits: Commit, @SerializedName("sha")val id:String)
