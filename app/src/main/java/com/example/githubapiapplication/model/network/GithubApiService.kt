package com.example.githubapiapplication.model.network

import com.example.githubapiapplication.model.data.RepoCommits
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.github.com/"

interface GithubApiService {

    @GET("repos/octocat/Hello-World/commits")
    fun getRepoCommits(): Call<List<RepoCommits>>

    companion object {
        var retrofitService: GithubApiService? = null


        val logging: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        /**
         * This method returns the instance of retrofitservice for getting Github repo commits for specified repository URL
         */
        fun getInstance(): GithubApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()
                retrofitService = retrofit.create(GithubApiService::class.java)
            }
            return retrofitService!!
        }
    }
}