package com.mudrichenko.audioplayer.api

import com.mudrichenko.audioplayer.api.pojo.responseSearch.ResponseSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointInterface {

    @GET("/search")
    fun requestSearchResult(@Query("term") keyword: String): Observable<ResponseSearch>

}