package com.mudrichenko.audioplayer.api.pojo.responseSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseSearch (
    @SerializedName("resultCount")         @Expose     var resultCount: Int,
    @SerializedName("results")             @Expose     var results: List<SearchResult>
)