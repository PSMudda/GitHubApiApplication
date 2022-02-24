package com.example.githubapiapplication

import android.app.Application
import com.example.githubapiapplication.di.APIComponent
import com.example.githubapiapplication.di.DaggerAPIComponent


class GitHubApiApplication : Application() {

    companion object {
        lateinit var apiComponent: APIComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        apiComponent = DaggerAPIComponent
            .builder()
            .build()
    }

}