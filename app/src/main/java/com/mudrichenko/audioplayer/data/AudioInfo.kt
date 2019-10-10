package com.mudrichenko.audioplayer.data

import java.io.Serializable

data class AudioInfo (
    val songName: String,
    val artistName: String,
    val imageUrl: String,
    val imageUrlBig: String,
    val audioUrl : String
): Serializable