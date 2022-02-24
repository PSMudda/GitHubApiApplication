package com.example.githubapiapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapiapplication.model.repo.CommitsRepository
import com.example.githubapiapplication.model.data.RepoCommits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommitViewModel @Inject constructor(val repository: CommitsRepository) : ViewModel() {

    val _commits = MutableLiveData<List<RepoCommits>>()

    val commits: LiveData<List<RepoCommits>> = _commits

    val errorMessage = MutableLiveData<String>()

    fun getRepoCommits() {

        val response = repository.getRepoCommits()

        response.enqueue(object : Callback<List<RepoCommits>> {
            override fun onResponse(
                call: Call<List<RepoCommits>>,
                response: Response<List<RepoCommits>>
            ) {
                _commits.postValue(response.body())
            }

            override fun onFailure(call: Call<List<RepoCommits>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}