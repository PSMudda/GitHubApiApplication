package com.example.githubapiapplication.di

import com.example.githubapiapplication.model.network.GithubApiService
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HttpModuleTest {

     var baseURL = "https://api.github.com/"

    var response = "[\n" +
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


    @Singleton
    @Provides
     fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return mockk()
    }

    @Singleton
    @Provides
      fun provideOKHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val okHttpClient: OkHttpClient = mock(OkHttpClient::class.java)

        val remoteCall: Call = mock(Call::class.java)

        val response: okhttp3.Response = okhttp3.Response.Builder()
            .request(Request.Builder().url(baseURL).build())
            .protocol(Protocol.HTTP_1_1)
            .code(200).message("").body(
                response
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .build()
        Mockito.`when`(remoteCall.execute()).thenReturn(response)

        Mockito.`when`(okHttpClient.newCall(MockitoHelper.anyObject())).thenReturn(remoteCall)

        return okHttpClient

    }
    object MockitoHelper {
        fun <T> anyObject(): T {
            Mockito.any<T>()
            return uninitialized()
        }
        @Suppress("UNCHECKED_CAST")
        fun <T> uninitialized(): T =  null as T
    }

    @Singleton
    @Provides
     fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
     fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
      fun getRepoApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }
}