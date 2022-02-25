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

enum class GithubApiDownloadStatus { LOADING, ERROR, DONE }

class CommitViewModel @Inject constructor(val repository: CommitsRepository) : ViewModel() {

    val _commits = MutableLiveData<List<RepoCommits>>()

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<GithubApiDownloadStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<GithubApiDownloadStatus> = _status

    val errorMessage = MutableLiveData<String>()

    fun getRepoCommits() {

        val response = repository.getRepoCommits()
        _status.value = GithubApiDownloadStatus.LOADING
        response.enqueue(object : Callback<List<RepoCommits>> {
            override fun onResponse(
                call: Call<List<RepoCommits>>,
                response: Response<List<RepoCommits>>
            ) {
                when(response.isSuccessful){
                    true->{
                        _status.value = GithubApiDownloadStatus.DONE
                        _commits.postValue(response.body())
                    }
                        else->{
                            errorMessage.postValue("Recieved Error Code :${response.code()}")
                            _status.value = GithubApiDownloadStatus.ERROR
                        }
                }
            }

            override fun onFailure(call: Call<List<RepoCommits>>, t: Throwable) {
                errorMessage.postValue(t.message)
                _status.value = GithubApiDownloadStatus.ERROR
            }
        })
    }

}