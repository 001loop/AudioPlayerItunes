package com.mudrichenko.audioplayer.api.pojo.responseSearch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResult (
    @SerializedName("wrapperType")              @Expose     var wrapperType: String,
    @SerializedName("kind")                     @Expose     var kind: String,
    @SerializedName("artistId")                 @Expose     var artistId: Long,
    @SerializedName("collectionId")             @Expose     var collectionId: Long,
    @SerializedName("trackId")                  @Expose     var trackId: Long,
    @SerializedName("artistName")               @Expose     var artistName: String,
    @SerializedName("collectionName")           @Expose     var collectionName: String,
    @SerializedName("trackName")                @Expose     var trackName: String,
    @SerializedName("collectionCensoredName")   @Expose     var collectionCensoredName: String,
    @SerializedName("trackCensoredName")        @Expose     var trackCensoredName: String,
    @SerializedName("artistViewUrl")            @Expose     var artistViewUrl: String,
    @SerializedName("collectionViewUrl")        @Expose     var collectionViewUrl: String,
    @SerializedName("trackViewUrl")             @Expose     var trackViewUrl: String,
    @SerializedName("previewUrl")               @Expose     var previewUrl: String,
    @SerializedName("artworkUrl60")             @Expose     var artworkUrl60: String,
    @SerializedName("artworkUrl100")            @Expose     var artworkUrl100: String,
    @SerializedName("collectionPrice")          @Expose     var collectionPrice: Float,
    @SerializedName("trackPrice")               @Expose     var trackPrice: Float,
    @SerializedName("collectionExplicitness")   @Expose     var collectionExplicitness: String,
    @SerializedName("trackExplicitness")        @Expose     var trackExplicitness: String,
    @SerializedName("discCount")                @Expose     var discCount: Int,
    @SerializedName("discNumber")               @Expose     var discNumber: Int,
    @SerializedName("trackCount")               @Expose     var trackCount: Int,
    @SerializedName("trackNumber")              @Expose     var trackNumber: Int,
    @SerializedName("trackTimeMillis")          @Expose     var trackTimeMillis: Int,
    @SerializedName("country")                  @Expose     var country: String,
    @SerializedName("currency")                 @Expose     var currency: String,
    @SerializedName("primaryGenreName")         @Expose     var primaryGenreName: String
)