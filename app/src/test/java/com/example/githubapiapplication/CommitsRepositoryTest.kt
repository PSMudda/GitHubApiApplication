package com.example.githubapiapplication

import com.example.githubapiapplication.di.APIComponentTest
import com.example.githubapiapplication.di.DaggerAPIComponentTest
import com.example.githubapiapplication.model.repo.CommitsRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CommitsRepositoryTest {

    companion object {
        lateinit var apiComponentTest: APIComponentTest
    }

    @Before
    fun setUp() {
        apiComponentTest = DaggerAPIComponentTest
            .builder()
            .build()
    }

    @Test
    fun getRepoCommits_testMethodCall() {
        val commitRepo:CommitsRepository = apiComponentTest.commitRepositoryInstance()

        val  actualResponse = commitRepo.getRepoCommits().execute()
        // Assert
        Assert.assertEquals(
            "200",
            actualResponse.code().toString()
        )
        Assert.assertEquals(actualResponse.body()?.size.toString(), "2")
    }

}