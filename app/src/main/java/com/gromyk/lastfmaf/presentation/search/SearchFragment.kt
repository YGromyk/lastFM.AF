package com.gromyk.lastfmaf.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.onTextChanged
import com.gromyk.lastfmaf.presentation.base.BaseFragment
import com.gromyk.lastfmaf.presentation.singers.SingerAdapter
import kotlinx.android.synthetic.main.progress_bar_layout.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SearchFragment : BaseFragment() {
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var adapter: SingerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeOnLiveDataVM()
    }

    private fun subscribeOnLiveDataVM() {
        viewModel.apply {
            artistsData.observe(this@SearchFragment, Observer(::onArtistsFound))
            isResultReceived.observe(this@SearchFragment, Observer(::onResultReceived))
        }
    }

    private fun onArtistsFound(artists: List<Artist>) {
        adapter.replaceItems(artists)
    }

    private fun onResultReceived(isResultReceived: Boolean) {
        progressBar.visibility =
                if (isResultReceived) View.GONE
                else View.VISIBLE
    }

    private fun initView() {
        adapter = SingerAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        searchArtistEditText.onTextChanged(viewModel::searchArtist)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}