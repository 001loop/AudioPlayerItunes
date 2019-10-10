package com.mudrichenko.audioplayer.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.mudrichenko.GlideApp
import com.mudrichenko.audioplayer.App
import com.mudrichenko.audioplayer.data.AudioInfo
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.mudrichenko.audioplayer.R


class AudioPlayerFragment: MvpAppCompatFragment(), AudioPlayerView {

    @InjectPresenter
    lateinit var mAudioPlayerPresenter: AudioPlayerPresenter

    lateinit var mTextViewSongName: TextView
    lateinit var mTextViewArtistName: TextView
    lateinit var mImageView: ImageView
    lateinit var mExoPlayer: SimpleExoPlayer
    lateinit var mExoPlayerView: PlayerView

    companion object {
        fun newInstance(audioInfo: AudioInfo): AudioPlayerFragment {
            val fragment = AudioPlayerFragment()
            val bundle = Bundle()
            bundle.putSerializable("audioInfo", audioInfo)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_audio_player, container, false)
        view.setOnTouchListener { view1, motionEvent -> true }
        // init views
        mImageView = view.findViewById(R.id.imageView)
        mTextViewSongName = view.findViewById(R.id.textViewName)
        mTextViewArtistName = view.findViewById(R.id.textViewArtist)
        mExoPlayerView = view.findViewById(R.id.exoPlayerView)
        // set data
        mAudioPlayerPresenter.startLoadAudio(arguments)
        return view
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer(mAudioPlayerPresenter.getMediaSource())
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || mExoPlayer == null) {
            initializePlayer(mAudioPlayerPresenter.getMediaSource())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeAudioPlayer()
    }

    init {
        App.appComponent!!.inject(this)
    }

    private fun initializePlayer(mediaSource: MediaSource) {
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(App.appContext),
            DefaultTrackSelector(), DefaultLoadControl()
        )
        mExoPlayer.prepare(mediaSource, true, false)
        mExoPlayerView.setPlayer(mExoPlayer)
        mExoPlayer.setPlayWhenReady(false)
    }

    override fun setSongName(songName: String) {
        mTextViewSongName.setText(songName)
    }

    override fun setArtistName(artistName: String) {
        mTextViewArtistName.setText(artistName)
    }

    override fun setMediaSource(mediaSource: MediaSource) {
        mExoPlayer.prepare(mediaSource, true, false)
    }

    override fun setImageUrl(imageUrl: String) {
        GlideApp.with(mImageView)
            .load(imageUrl)
            .placeholder(resources.getDrawable(R.drawable.image_default))
            .centerCrop()
            .into(mImageView)
    }

    fun removeAudioPlayer() {
        mExoPlayer.stop()
        mExoPlayer.release()
    }



}