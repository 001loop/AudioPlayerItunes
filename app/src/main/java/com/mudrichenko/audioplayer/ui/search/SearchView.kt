package com.mudrichenko.audioplayer.ui.search

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mudrichenko.audioplayer.data.AudioInfo

@StateStrategyType(AddToEndStrategy::class)
interface SearchView : MvpView {

    fun showProgressWheel()

    fun hideProgressWheel()

    @StateStrategyType(SkipStrategy::class)
    fun showSnackbarMessage(message: String)

    fun showSearchResults(recyclerViewItems: ArrayList<AudioInfo>)

}