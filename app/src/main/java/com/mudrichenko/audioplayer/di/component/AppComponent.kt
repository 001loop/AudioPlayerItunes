package com.mudrichenko.audioplayer.di.component

import com.mudrichenko.audioplayer.di.module.AppModule
import com.mudrichenko.audioplayer.data.repository.SearchRepository
import com.mudrichenko.audioplayer.di.module.DataModule
import com.mudrichenko.audioplayer.di.module.RetrofitModule
import com.mudrichenko.audioplayer.di.module.UtilsModule
import com.mudrichenko.audioplayer.ui.player.AudioPlayerFragment
import com.mudrichenko.audioplayer.ui.player.AudioPlayerPresenter
import com.mudrichenko.audioplayer.ui.search.SearchFragment
import com.mudrichenko.audioplayer.ui.search.SearchPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, UtilsModule::class, DataModule::class])
interface AppComponent {

    // Fragments
    fun inject(fragmentAudio: AudioPlayerFragment)

    fun inject(fragmentSearch: SearchFragment)

    // Repositories
    fun inject(repository: SearchRepository)

    // Presenters
    fun inject(presenterAudio: AudioPlayerPresenter)

    fun inject(presenterSearch: SearchPresenter)

}