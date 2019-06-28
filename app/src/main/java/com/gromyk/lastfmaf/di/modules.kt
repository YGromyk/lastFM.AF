package com.gromyk.lastfmaf.di

import com.gromyk.lastfmaf.domain.repository.DataRepository
import com.gromyk.lastfmaf.presentation.albumdetails.AlbumDetailsViewModel
import com.gromyk.lastfmaf.presentation.search.SearchViewModel
import com.gromyk.lastfmaf.presentation.albums.AlbumsViewModel
import com.gromyk.persistence.db.DatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { AlbumsViewModel() }
    viewModel { AlbumDetailsViewModel() }
    viewModel { SearchViewModel() }
}

val repository = module {
    single { DatabaseBuilder.buildDatabase(androidContext(), "appDB") }
    single { DataRepository() }
}