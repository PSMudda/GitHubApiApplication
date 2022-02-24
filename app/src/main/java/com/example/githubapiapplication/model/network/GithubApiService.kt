package com.example.githubapiapplication.model.network

import com.example.githubapiapplication.model.data.RepoCommits
import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService {

    @GET("repos/StylishThemes/GitHub-Dark/commits")
    fun getRepoCommits(): Call<List<RepoCommits>>
}