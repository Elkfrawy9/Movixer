package com.elkfrawy.movixer.di

import com.elkfrawy.movixer.data.remote.movie.MovieDataSource
import com.elkfrawy.movixer.data.remote.movie.MovieDataSourceImpl
import com.elkfrawy.movixer.data.remote.people.PeopleRemoteSource
import com.elkfrawy.movixer.data.remote.people.PeopleRemoteSourceImpl
import com.elkfrawy.movixer.data.repository.PeopleRepositoryImpl
import com.elkfrawy.movixer.data.remote.series.SeriesDataSource
import com.elkfrawy.movixer.data.remote.series.SeriesDataSourceImpl
import com.elkfrawy.movixer.data.repository.MovieRepositoryImpl
import com.elkfrawy.movixer.data.repository.SearchRepositoryImpl
import com.elkfrawy.movixer.data.repository.SeriesRepositoryImpl
import com.elkfrawy.movixer.domain.repository.MovieRepository
import com.elkfrawy.movixer.domain.repository.PeopleRepository
import com.elkfrawy.movixer.domain.repository.SearchRepository
import com.elkfrawy.movixer.domain.repository.SeriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    @Singleton
    abstract fun provideMovieDataSource(movieSource: MovieDataSourceImpl): MovieDataSource

    @Binds
    @Singleton
    abstract fun provideMovieRepo(movieRepo: MovieRepositoryImpl): MovieRepository


    @Binds
    @Singleton
    abstract fun provideSeriesDataSource(seriesSource: SeriesDataSourceImpl): SeriesDataSource

    @Binds
    @Singleton
    abstract fun provideSeriesRepo(seriesRepo: SeriesRepositoryImpl): SeriesRepository

    @Binds
    @Singleton
    abstract fun provideSearchRepo(searchRepo: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun providePeopleDataSource(peopleSource: PeopleRemoteSourceImpl): PeopleRemoteSource

    @Binds
    @Singleton
    abstract fun providePeopleRepo(peopleRepo: PeopleRepositoryImpl): PeopleRepository





}