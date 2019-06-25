package com.gromyk.lastfmaf.presentation.albumdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.helpers.TimeHelper
import com.gromyk.lastfmaf.helpers.loadPhoto
import com.gromyk.lastfmaf.presentation.FragmentParameters
import com.gromyk.lastfmaf.presentation.base.BaseFragment
import com.gromyk.lastfmaf.presentation.navigation.Navigator
import com.gromyk.lastfmaf.presentation.navigation.Screen
import com.gromyk.lastfmaf.presentation.pojos.imageLinkPersistence
import com.gromyk.lastfmaf.presentation.songs.TrackAdapter
import com.gromyk.lastfmaf.presentation.views.PlaceholderType
import com.gromyk.persistence.composedalbum.AlbumObject
import kotlinx.android.synthetic.main.album_details_fragment.*
import kotlinx.android.synthetic.main.list_content.*
import kotlinx.android.synthetic.main.progress_bar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumDetailsFragment : BaseFragment() {
    override val viewModel by viewModel<AlbumDetailsViewModel>()
    private lateinit var adapter: TrackAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.album_details_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getExtras()
        initViewModel()
        viewModel.fetchData()
    }

    private fun initView() {
        adapter = TrackAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        infoButton.setOnClickListener { openInfo() }
    }

    private fun initViewModel() {
        viewModel.apply {
            albumData.observe(this@AlbumDetailsFragment, Observer(::onAlbumLoaded))
            isAlbumLoaded.observe(this@AlbumDetailsFragment, Observer(::onAlbumIsLoaded))
        }
    }

    private fun onAlbumLoaded(album: AlbumObject) {
        albumImageView.loadPhoto(album.images.imageLinkPersistence())
        albumNameTextView.text = album.album.name
        albumSingerTextView.text = viewModel.artist
        durationTextView.text = TimeHelper.formatToMMSS(
            album.tracks
                .map { it.duration.toInt() }.sum()
        )
        publishedDateTextView.text = album.wiki.published ?: "Unknown"
        adapter.replaceItems(album.tracks)
        placeholder?.showPlaceholder(album.tracks.count(), PlaceholderType.NO_SONGS)
    }

    private fun onAlbumIsLoaded(isLoaded: Boolean) {
        val buttonsVisibility = if (isLoaded) View.VISIBLE else View.GONE
        if (isLoaded) {
            progressBar.visibility = View.GONE
        } else {
            progressBar.visibility = View.VISIBLE
        }
        infoButton.visibility = buttonsVisibility
    }

    private fun openInfo() {
        val url = viewModel.albumData.value?.album?.url
        val bundle = bundleOf(
            FragmentParameters.URL to url
        )
        navigator.navigateTo(Screen.OPEN_WEB, bundle)
    }

    private fun getExtras() {
        arguments?.apply {
            val showAlertIfNullAndReturnValue = { str: String? ->
                str ?: showError(
                    getString(R.string.error),
                    getString(R.string.you_have_to_select_album)
                )
                str
            }
            viewModel.artist = showAlertIfNullAndReturnValue(getString(FragmentParameters.ARTIST_KEY))
            viewModel.album = showAlertIfNullAndReturnValue(getString(FragmentParameters.ALBUM_KEY))
            viewModel.loadLocalData = getBoolean(FragmentParameters.LOAD_LOCAL_DATA)
        }
    }

    companion object {
        fun newInstance(
            parameters: Bundle? = null,
            navigator: Navigator
        ) = AlbumDetailsFragment().apply {
            arguments = parameters
            this.navigator = navigator
        }
    }
}