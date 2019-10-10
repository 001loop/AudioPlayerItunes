package com.mudrichenko.audioplayer.di.module

import android.content.Context
import com.mudrichenko.audioplayer.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    internal fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

}