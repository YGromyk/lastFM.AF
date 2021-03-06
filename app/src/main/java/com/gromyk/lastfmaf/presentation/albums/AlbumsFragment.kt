package com.gromyk.lastfmaf.presentation.albums

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gromyk.api.dtos.artist.Artist
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.loadPhoto
import com.gromyk.lastfmaf.presentation.FragmentParameters
import com.gromyk.lastfmaf.presentation.base.BaseFragment
import com.gromyk.lastfmaf.presentation.pojos.AlbumUI
import com.gromyk.lastfmaf.presentation.pojos.imageLinkAPI
import com.gromyk.lastfmaf.presentation.views.PlaceholderType
import kotlinx.android.synthetic.main.albums_fragment.*
import kotlinx.android.synthetic.main.artist_info.*
import kotlinx.android.synthetic.main.list_content.*
import kotlinx.android.synthetic.main.progress_bar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.res.Configuration


class AlbumsFragment : BaseFragment(), AlbumsAdapter.OnSaveAlbum {
    override val viewModel by viewModel<AlbumsViewModel>()
    private lateinit var adapter: AlbumsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.albums_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtras()
        initView()
        subscribeOnLiveDataVM()
        viewModel.fetchData()
    }

    private fun subscribeOnLiveDataVM() {
        viewModel.apply {
            topAlbums.observe(this@AlbumsFragment, Observer(::onAlbumsLoaded))
            artistInfo.observe(this@AlbumsFragment, Observer(::onArtistLoaded))
            isResultReceived.observe(this@AlbumsFragment, Observer(::onTopAlbumsLoaded))
        }
    }


    private fun initView() {
        adapter = AlbumsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, getSpanCount())
        swipeRefreshLayout.setOnRefreshListener {
            if (viewModel.isResultReceived.value == true)
                viewModel.fetchData()
        }
        infoButton.setOnClickListener {
            moreInfoExpandableLayout?.apply {
                isExpanded = !isExpanded
            }
        }
        if (viewModel.loadLocalData) {
            artistInfoLayout.visibility = View.GONE
        } else {
            artistInfoLayout.visibility = View.VISIBLE
        }
    }

    private fun onAlbumsLoaded(albums: List<AlbumUI>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.replaceItems(albums)
        val itemsCount = albums.count()
        placeholder?.showPlaceholder(itemsCount, PlaceholderType.NO_ALBUMS)
        if (itemsCount == 0)
            swipeRefreshLayout.visibility = View.GONE
        else
            swipeRefreshLayout.visibility = View.VISIBLE
    }

    private fun onTopAlbumsLoaded(areTopAlbumsLoaded: Boolean) {
        if (areTopAlbumsLoaded) {
            progressBar.visibility = View.GONE
        } else {
            progressBar.visibility = View.VISIBLE
        }
    }

    @Suppress("Warnings")
    private fun onArtistLoaded(artist: Artist) {
        artistImageView.loadPhoto(artist.image?.imageLinkAPI() ?: "")
        singerTextView.text = "Artist: ${artist.name}"
        artist.stats?.playcount?.let { playCountTextView.text = "Play count: $it" }
        artist.stats?.listeners?.let { listenersTextView.text = "Listeners: $it" }
        val wiki = artist.bio?.summary ?: ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            artistWikiTextView.text = Html.fromHtml(wiki, Html.FROM_HTML_MODE_COMPACT)
        } else {
            artistWikiTextView.text = Html.fromHtml(wiki)
        }
    }

    override fun saveAlbum(albumUI: AlbumUI) {
        viewModel.saveAlbumAndArtist(albumUI.name, albumUI.artist)
    }

    override fun removeAlbum(albumUI: AlbumUI) {
        viewModel.removeAlbum(albumUI.name, albumUI.artist)
    }

    override fun openAlbumDetails(albumUI: AlbumUI) {
        val bundle = bundleOf(
                FragmentParameters.ALBUM_KEY to albumUI.name,
                FragmentParameters.ARTIST_KEY to albumUI.artist,
                FragmentParameters.LOAD_LOCAL_DATA to viewModel.loadLocalData
        )
        val action =
                if (viewModel.loadLocalData)
                    R.id.action_albumsFragment_to_albumDetailsFragment
                else
                    R.id.action_albumsFragment2_to_albumDetailsFragment2
        getNavController()?.navigate(action, bundle)
    }

    private fun getSpanCount(): Int {
        val currentOrientation = resources.configuration.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            return LANDSCAPE_SPAN_COUNT
        } else {
            return PORTRAIT_SPAN_COUNT
        }
    }

    private fun getExtras() {
        arguments?.apply {
            getString(FragmentParameters.ARTIST_KEY)?.let {
                viewModel.searchedArtist = it
                viewModel.loadLocalData = false
            }
        }
    }

    companion object {
        private const val PORTRAIT_SPAN_COUNT = 2
        private const val LANDSCAPE_SPAN_COUNT = 3
    }

}