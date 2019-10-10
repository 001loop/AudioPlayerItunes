package com.mudrichenko.audioplayer.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.mudrichenko.audioplayer.App
import com.mudrichenko.audioplayer.R
import com.mudrichenko.audioplayer.SearchResultsViewAdapter
import com.mudrichenko.audioplayer.data.AudioInfo
import java.util.ArrayList

class SearchFragment: MvpAppCompatFragment(), SearchView, SearchResultsViewAdapter.OnItemClickListener {

    @InjectPresenter
    lateinit var mSearchPresenter: SearchPresenter

    private lateinit var mMainLayout: ConstraintLayout

    private lateinit var mEditTextSearch: EditText

    private lateinit var mButtonViewSearch: Button

    private lateinit var mRecyclerViewItems: ArrayList<AudioInfo>

    private lateinit var mSearchResultsAdapter: SearchResultsViewAdapter

    private lateinit var mPhotosRecyclerView: RecyclerView

    private lateinit var mFragmentContainer: FrameLayout

    private lateinit var mProgressBar: ProgressBar

    companion object {

        private var mSearchFragmentListener: SearchFragmentListener? = null

        fun newInstance(searchFragmentListener: SearchFragmentListener): SearchFragment {
            val fragment = SearchFragment()
            mSearchFragmentListener = searchFragmentListener
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val mContext = context
        view.setOnTouchListener { view1, motionEvent -> true }
        // init views
        mMainLayout = view.findViewById(R.id.main_layout)
        mFragmentContainer = view.findViewById(R.id.fragmentContainer)
        mEditTextSearch = view.findViewById(R.id.editTextSearch)
        mButtonViewSearch = view.findViewById(R.id.buttonSearch)
        mButtonViewSearch.setOnClickListener { onSearchClicked() }
        mProgressBar = view.findViewById(R.id.progressBar)
        mProgressBar.visibility = View.INVISIBLE
        // recyclerView elements
        mRecyclerViewItems = ArrayList()
        mSearchResultsAdapter = SearchResultsViewAdapter(mContext!!, mRecyclerViewItems)
        mSearchResultsAdapter.setOnItemClickListener(this)
        mPhotosRecyclerView = view.findViewById(R.id.recyclerViewSearchResults)
        mPhotosRecyclerView.adapter = mSearchResultsAdapter
        val gridLayoutManager = GridLayoutManager(mContext, 1)
        mPhotosRecyclerView.layoutManager = gridLayoutManager
        return view
    }

    init {
        App.appComponent!!.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mSearchPresenter.unsubscribeFromRepository()
    }
    override fun onItemClick(position: Int) {
        if (mSearchFragmentListener != null) {
            hideKeyboard()
            mSearchFragmentListener?.onAudioSelected(mRecyclerViewItems[position])
        }
    }

    private fun onSearchClicked() {
        mSearchPresenter.onSearchClicked(mEditTextSearch.text.toString())
    }

    override fun showProgressWheel() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressWheel() {
        mProgressBar.visibility = View.INVISIBLE
    }

    override fun showSnackbarMessage(message: String) {
        Snackbar.make(mMainLayout, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showSearchResults(recyclerViewItems: ArrayList<AudioInfo>) {
        mRecyclerViewItems.addAll(recyclerViewItems)
        mSearchResultsAdapter.notifyDataSetChanged()
    }

    interface SearchFragmentListener {
        fun onAudioSelected(audioInfo: AudioInfo)
    }

    fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.getWindowToken(), 0)
    }

}