package com.example.githubapiapplication.model.repo

import com.example.githubapiapplication.model.network.GithubApiService

class CommitsRepository constructor(private val githubApiService: GithubApiService) {


    fun getRepoCommits() =
        githubApiService.getRepoCommits()
}