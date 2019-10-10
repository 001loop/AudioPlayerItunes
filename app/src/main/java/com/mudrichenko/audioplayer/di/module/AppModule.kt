package com.mudrichenko.audioplayer.di.module

import android.content.Context
import com.mudrichenko.audioplayer.data.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val mAppContext: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mAppContext
    }

    @Provides
    @Named("SearchRepository")
    fun provideSearchRepository(): SearchRepository = SearchRepository()

}