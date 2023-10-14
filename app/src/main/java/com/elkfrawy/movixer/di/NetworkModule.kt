package com.elkfrawy.movixer.di

import com.elkfrawy.movixer.data.BASE_URL
import com.elkfrawy.movixer.data.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)


    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient().newBuilder().addInterceptor {
            val request = it.request().newBuilder().addHeader("accept", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZGY5OTc5OTk5ZjQ1MDE2MmI1YjBhM2ZlMTY2YTg5MCIsInN1YiI6IjY0ZjA1YzNlY2FhNTA4MDEwYWU2ODkwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qz51ZqyCqs_bKOhMVADqYs7naGf8XyGEnTA4VDH0DSI"
                ).build()

            it.proceed(request)
        }.addInterceptor(interceptor).build()

        return client
    }

}