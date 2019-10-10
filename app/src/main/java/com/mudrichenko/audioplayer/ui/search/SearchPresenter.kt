package com.mudrichenko.audioplayer.ui.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.mudrichenko.audioplayer.App
import com.mudrichenko.audioplayer.R
import com.mudrichenko.audioplayer.api.pojo.responseSearch.SearchResult
import com.mudrichenko.audioplayer.data.AudioInfo
import com.mudrichenko.audioplayer.data.repository.SearchRepository
import com.mudrichenko.audioplayer.utils.NetworkUtils
import javax.inject.Inject

@InjectViewState
class SearchPresenter : MvpPresenter<SearchView>(), SearchRepository.SearchRepositoryListener {

    private val minKeywordLength = 5

    @Inject
    lateinit var mNetworkUtils: NetworkUtils

    @Inject
    lateinit var mSearchRepository: SearchRepository

    private var mIsInternetConnectionAvailable: Boolean = false

    override fun onFirstViewAttach() {
        mSearchRepository.setSearchRepositoryListener(this)
        mIsInternetConnectionAvailable = mNetworkUtils.isInternetConnectionAvailable()
    }

    init {
        App.appComponent!!.inject(this)
    }

    fun onSearchClicked(keyword: String?) {
        if (!mNetworkUtils.isInternetConnectionAvailable()) {
            viewState.showSnackbarMessage(App.appContext!!.getString(R.string.no_internet_connection))
            return
        }
        if (keyword == null || keyword.length < minKeywordLength) {
            viewState.showSnackbarMessage(App.appContext!!.getString(R.string.search_error_few_characters))
            return
        }
        mSearchRepository.startSearchTask(keyword)
        viewState.showProgressWheel()
    }

    override fun onSearchResultsReceived(searchResults: List<SearchResult>) {
        viewState.hideProgressWheel()
        viewState.showSearchResults(convertToAudioInfoList(searchResults))
    }

    override fun onErrorReceived(errorText: String) {
        viewState.showSnackbarMessage(errorText)
        viewState.hideProgressWheel()
    }

    fun unsubscribeFromRepository() {
        mSearchRepository.unsubscribe()
    }

    fun convertToAudioInfoList(searchResults: List<SearchResult>): ArrayList<AudioInfo> {
        val audioInfoArray = ArrayList<AudioInfo>()
        for (x in searchResults.indices) {
            val audioInfo = AudioInfo(
                searchResults[x].trackName,
                searchResults[x].artistName,
                searchResults[x].artworkUrl60,
                searchResults[x].artworkUrl100,
                searchResults[x].previewUrl)
            audioInfoArray.add(audioInfo)
        }
        return audioInfoArray
    }

}