package com.mudrichenko.audioplayer.di.module

import com.mudrichenko.audioplayer.data.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideSearchRepository(): SearchRepository {
        return SearchRepository()
    }

}