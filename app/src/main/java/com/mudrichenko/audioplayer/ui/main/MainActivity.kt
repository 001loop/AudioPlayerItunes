package com.mudrichenko.audioplayer.ui.main

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.mudrichenko.audioplayer.R
import com.mudrichenko.audioplayer.data.AudioInfo
import com.mudrichenko.audioplayer.ui.player.AudioPlayerFragment
import com.mudrichenko.audioplayer.ui.search.SearchFragment

class MainActivity : MvpAppCompatActivity(), SearchFragment.SearchFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showSearchFragment()
        }
    }

    private fun showSearchFragment() {
        val searchFragment = SearchFragment.newInstance(this)
        //searchFragment.setSearchFragmentListener(this)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, searchFragment)
        fragmentTransaction.commit()
    }

    private fun showPlayerFragment(audioInfo : AudioInfo) {
        val playerFragment = AudioPlayerFragment.newInstance(audioInfo)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_layout, playerFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onAudioSelected(audioInfo: AudioInfo) {
        showPlayerFragment(audioInfo)
    }


}