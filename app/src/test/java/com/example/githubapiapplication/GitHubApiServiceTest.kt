package com.example.githubapiapplication

import com.example.githubapiapplication.di.HttpModule
import com.example.githubapiapplication.model.network.GithubApiService
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.net.HttpURLConnection


class GitHubApiServiceTest {
    lateinit var mockWebServer:MockWebServer
    lateinit var response:String

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        response = "[\n" +
                "  {\n" +
                "    \"sha\": \"7fd1a60b01f91b314f59955a4e4d4e80d8edf11d\",\n" +
                "    \"commit\": {\n" +
                "      \"author\": {\n" +
                "        \"name\": \"The Octocat\"\n" +
                "      },\n" +
                "      \"message\": \"Merge pull request #6 from Spaceghost/patch-1\\n\\nNew line at end of file.\",\n" +
                "      \"tree\": {\n" +
                "        \"sha\": \"b4eecafa9be2f2006ce1b709d6857b07069b4608\"\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"sha\": \"7fd1a60b01f91b314f59955a4e4d4e80d8edf11d\",\n" +
                "    \"commit\": {\n" +
                "      \"author\": {\n" +
                "        \"name\": \"The Octocat\"\n" +
                "      },\n" +
                "      \"message\": \"Merge pull request #6 from Spaceghost/patch-1\\n\\nNew line at end of file.\",\n" +
                "      \"tree\": {\n" +
                "        \"sha\": \"b4eecafa9be2f2006ce1b709d6857b07069b4608\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "]"
    }

    @After
    fun cleanUp() {
        mockWebServer.shutdown()
    }

    @Test
    fun test200Response() {

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(response)
        mockWebServer.enqueue(response)

        val httpModule= HttpModule()
        httpModule.baseURL="http://localhost:8080"
        val api:GithubApiService=httpModule.getRepoApiService(
            httpModule.provideRetrofit(httpModule.provideGSON(), httpModule.provideOKHttpClient(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            )))

        val  actualResponse = api.getRepoCommits().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
        assertEquals(actualResponse.body()?.size.toString(), "2")
    }

    @Test
    fun test404Response() {

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)

        mockWebServer.enqueue(response)

        val httpModule= HttpModule()
        httpModule.baseURL="http://localhost:8080"
        val api:GithubApiService=httpModule.getRepoApiService(
            httpModule.provideRetrofit(httpModule.provideGSON(), httpModule.provideOKHttpClient(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            )))

        val  actualResponse = api.getRepoCommits().execute()
        // Assert
        assertEquals(response.toString().contains("404"),actualResponse.code().toString().contains("404"))
        assertNull(actualResponse.body())
    }
}