package com.gromyk.api.di

import com.gromyk.api.Api
import com.gromyk.api.ApiFactory
import com.gromyk.api.services.ArtistService
import com.gromyk.api.services.SearchService
import org.koin.dsl.module

val api = module {
    single { ApiFactory.retrofit().create(ArtistService::class.java) }
    single { ApiFactory.retrofit().create(SearchService::class.java) }
    single { Api() }
}