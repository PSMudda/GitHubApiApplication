package com.example.githubapiapplication.di

import com.example.githubapiapplication.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [HttpModule::class])
interface APIComponent {
    fun inject(mainActivity: MainActivity)
}