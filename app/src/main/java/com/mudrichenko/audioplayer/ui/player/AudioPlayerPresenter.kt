package com.mudrichenko.audioplayer.ui.player

import android.net.Uri
import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.mudrichenko.audioplayer.App
import com.mudrichenko.audioplayer.data.AudioInfo

@InjectViewState
class AudioPlayerPresenter: MvpPresenter<AudioPlayerView>() {

    private lateinit var mMediaSource: MediaSource

    init {
        App.appComponent!!.inject(this)
    }

    fun startLoadAudio(bundle: Bundle?) {
        if (bundle != null) {
            val audioInfo = bundle.getSerializable("audioInfo") as AudioInfo
            viewState.setSongName(audioInfo.songName)
            viewState.setArtistName(audioInfo.artistName)
            viewState.setImageUrl(audioInfo.imageUrlBig)
            mMediaSource = buildMediaSource(Uri.parse(audioInfo.audioUrl))
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer-codelab")
        ).createMediaSource(uri)
    }

    fun getMediaSource() = mMediaSource
}