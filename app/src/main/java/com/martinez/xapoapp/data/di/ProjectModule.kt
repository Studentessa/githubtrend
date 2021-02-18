package com.martinez.xapoapp.data.di

import com.martinez.xapoapp.BuildConfig
import com.martinez.xapoapp.data.mapper.LanguagePresentationMapper
import com.martinez.xapoapp.data.mapper.ProjectPresentationMapper
import com.martinez.xapoapp.data.remote.ProjectService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ProjectModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.SERVER_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)

        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ProjectService::class.java)

    @Provides
    @Singleton
    fun provideProjectMapper() = ProjectPresentationMapper()

    @Provides
    @Singleton
    fun provideLanguageMapper() = LanguagePresentationMapper()

}