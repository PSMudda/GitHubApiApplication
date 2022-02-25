package com.example.githubapiapplication.di

import com.example.githubapiapplication.model.repo.CommitsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModuleTest::class])
interface APIComponentTest {
    fun commitRepositoryInstance():CommitsRepository
}

