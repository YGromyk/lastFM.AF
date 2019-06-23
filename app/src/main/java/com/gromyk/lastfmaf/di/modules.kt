package com.gromyk.lastfmaf.di

import com.gromyk.lastfmaf.presentation.albumdetails.AlbumDetailsViewModel
import com.gromyk.lastfmaf.presentation.topalbums.TopAlbumsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { TopAlbumsViewModel() }
    viewModel { AlbumDetailsViewModel() }
}