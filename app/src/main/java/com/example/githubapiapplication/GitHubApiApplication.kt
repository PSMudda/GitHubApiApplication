package com.example.githubapiapplication

import android.app.Application
import android.content.Context
import com.example.githubapiapplication.di.APIComponent
import com.example.githubapiapplication.di.DaggerAPIComponent


class GitHubApiApplication : Application() {

    companion object {
       // var ctx: Context? = null
        lateinit var apiComponent: APIComponent

    }


    override fun onCreate() {
        super.onCreate()
        apiComponent=initDaggerComponent()
    }

    private fun initDaggerComponent():APIComponent {
        apiComponent = DaggerAPIComponent
            .builder()
            .build()
        return apiComponent
    }

    fun getAPIComponentInstance():APIComponent{
        return apiComponent
    }
}