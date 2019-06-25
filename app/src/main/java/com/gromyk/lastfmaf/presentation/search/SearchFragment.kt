package com.gromyk.lastfmaf.presentation.search

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.FragmentParameters
import com.gromyk.lastfmaf.presentation.base.BaseFragment
import com.gromyk.lastfmaf.presentation.navigation.Screen
import com.gromyk.lastfmaf.presentation.singers.ArtistAdapter
import com.gromyk.lastfmaf.presentation.views.PlaceholderType
import kotlinx.android.synthetic.main.list_content.*
import kotlinx.android.synthetic.main.progress_bar_layout.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SearchFragment : BaseFragment(), ArtistAdapter.OnArtistSelected {
    override val viewModel by viewModel<SearchViewModel>()
    private lateinit var adapter: ArtistAdapter

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
        placeholder?.showPlaceholder(
            artists.count(),
            PlaceholderType.NO_ARTISTS,
            viewModel.lastSearched
        )
    }

    private fun onResultReceived(isResultReceived: Boolean) {
        progressBar.visibility =
            if (isResultReceived) View.GONE
            else View.VISIBLE
    }

    private fun initView() {
        adapter = ArtistAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        searchView.inputType = InputType.TYPE_CLASS_TEXT
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    search(newText)
                }
                return false
            }
        })
        
        placeholder?.showPlaceholder(0, PlaceholderType.SEARCH)
        viewModel.isResultReceived.value = true
    }

    private fun search(text: String) {
        if (text.isBlank() || text.isEmpty()) {
            placeholder?.showPlaceholder(0, PlaceholderType.SEARCH)
        } else {
            placeholder?.showPlaceholder(1, PlaceholderType.SEARCH)
            viewModel.searchArtist(text)
        }
    }

    override fun onArtistSelected(artist: Artist) {
        val parameters = bundleOf(
            FragmentParameters.ARTIST_KEY to artist.name
        )
        navigator.navigateTo(Screen.ARTIST_DETAILS, parameters)
    }

    companion object {
        fun newInstance() = SearchFragment().apply {

        }
    }
}