package com.mudrichenko.audioplayer.data.repository

import com.mudrichenko.audioplayer.App
import com.mudrichenko.audioplayer.R
import com.mudrichenko.audioplayer.api.EndpointInterface
import com.mudrichenko.audioplayer.api.pojo.responseSearch.ResponseSearch
import com.mudrichenko.audioplayer.api.pojo.responseSearch.SearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository {

    private var mSearchRepositoryListener: SearchRepositoryListener? = null

    @Inject
    lateinit var mEndpointInterface: EndpointInterface

    var disposables: ArrayList<Disposable>? = null

    init {
        App.appComponent!!.inject(this)
        disposables = ArrayList()
    }

    fun startSearchTask(keyword: String) {
        unsubscribe()
        val disposable = mEndpointInterface.requestSearchResult(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getSearchResultsObserver())
        disposables?.add(disposable)
    }

    private fun getSearchResultsObserver(): DisposableObserver<ResponseSearch> {
        return object : DisposableObserver<ResponseSearch>() {
            override fun onComplete() {

            }

            override fun onNext(response: ResponseSearch) {
                if (response.resultCount > 0) {
                    onSearchResultsReceived(response.results)
                } else {
                    onErrorReceived(App.appContext!!.getString(R.string.repository_no_data))
                }
            }

            override fun onError(e: Throwable) {
                onErrorReceived(App.appContext!!.getString(R.string.repository_unknown_error))
            }
        }
    }

    private fun onErrorReceived(errorText: String) {
        if (mSearchRepositoryListener != null) {
            mSearchRepositoryListener?.onErrorReceived(errorText)
        }
    }

    private fun onSearchResultsReceived(searchResults: List<SearchResult>) {
        if (mSearchRepositoryListener != null) {
            mSearchRepositoryListener?.onSearchResultsReceived(searchResults)
        }
    }

    interface SearchRepositoryListener {
        fun onSearchResultsReceived(searchResults: List<SearchResult>)
        fun onErrorReceived(errorText: String)
    }

    fun setSearchRepositoryListener(searchRepositoryListener: SearchRepositoryListener) {
        mSearchRepositoryListener = searchRepositoryListener
    }

    fun unsubscribe() {
        if (disposables == null) {
            return
        }
        for (x in disposables!!.indices) {
            val disposable = disposables!![x]
            if (!disposable.isDisposed) {
                disposable.dispose()
            }
        }
        disposables?.clear()
    }


}

