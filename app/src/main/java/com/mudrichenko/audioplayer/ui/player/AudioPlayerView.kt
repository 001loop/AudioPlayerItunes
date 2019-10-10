package com.mudrichenko.audioplayer.ui.player

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.android.exoplayer2.source.MediaSource

@StateStrategyType(AddToEndStrategy::class)
interface AudioPlayerView : MvpView {

    fun setMediaSource(mediaSource: MediaSource)

    fun setSongName(songName: String)

    fun setArtistName(artistName: String)

    fun setImageUrl(imageUrl: String)

}