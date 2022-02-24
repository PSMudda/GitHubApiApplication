package com.example.githubapiapplication.model.data

import com.google.gson.annotations.SerializedName

data class RepoCommit(@SerializedName("author")val author: Author, @SerializedName("tree") val tree: Tree, @SerializedName("message") val message:String)
