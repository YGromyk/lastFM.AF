package com.gromyk.lastfmaf.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.topalbums.TopAlbumsFragment
import com.gromyk.lastfmaf.presentation.topalbums.TopAlbumsViewModel

class MainActivity : AppCompatActivity(), Navigator {
    lateinit var viewModel: TopAlbumsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TopAlbumsViewModel::class.java)
//        viewModel.fetchArtistInfo("Suicide Boys")
//        viewModel.searchArtist("kendrick lamar")
//        viewModel.fetchAlbumsInfo("Cher","Believe")
        startFragment()
    }

    private fun startFragment() {
        val tag = TopAlbumsFragment::class.java.simpleName
        if (supportFragmentManager.findFragmentByTag(tag) == null)
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, TopAlbumsFragment.newInstance(navigator = this), tag)
                    .commit()
    }

    override fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
