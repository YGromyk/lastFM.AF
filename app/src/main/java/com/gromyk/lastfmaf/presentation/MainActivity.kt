package com.gromyk.lastfmaf.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.topalbums.TopAlbumsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: TopAlbumsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TopAlbumsViewModel::class.java)
        viewModel.fetchTopAlbums()
        viewModel.searchArtist("kendrick lamar")
    }
}
