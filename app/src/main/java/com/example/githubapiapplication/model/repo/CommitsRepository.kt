package com.example.githubapiapplication.model.repo

import com.example.githubapiapplication.model.network.GithubApiService
import javax.inject.Inject

class CommitsRepository @Inject constructor(val githubApiService: GithubApiService) {

    fun getRepoCommits() =
        githubApiService.getRepoCommits()
}