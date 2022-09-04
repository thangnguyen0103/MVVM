package com.thangnguyen.demomvvm.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thangnguyen.demomvvm.data.remote.GithubApi
import com.thangnguyen.demomvvm.data.repository.FakeRepoRepository
import com.thangnguyen.demomvvm.data.repository.RepoRepository
import com.thangnguyen.demomvvm.data.repository.RepoRepositoryImpl
import com.thangnguyen.demomvvm.util.DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

val appModule = module {
    // data/repository
//    single<RepoRepository> { RepoRepositoryImpl(get()) }

    single<RepoRepository> { FakeRepoRepository(get()) }

    // data/api
    single {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
    single {
        OkHttpClient.Builder()
            .addNetworkInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Moshi.Builder()
            .add(Date::class.java, DateJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(GithubApi.HTTPS_API_GITHUB_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    single {
        get<Retrofit>().create(GithubApi::class.java)
    }

}
