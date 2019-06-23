package com.gromyk.lastfmaf.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.search.SearchFragment
import com.gromyk.lastfmaf.presentation.topalbums.TopAlbumsFragment
import com.gromyk.lastfmaf.presentation.topalbums.TopAlbumsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    Navigator,
    BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var viewModel: TopAlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TopAlbumsViewModel::class.java)
//        viewModel.fetchArtistInfo("Suicide Boys")
//        viewModel.searchArtist("kendrick lamar")
//        viewModel.fetchAlbumsInfo("Cher","Believe")
        initView()
        startFragment(TopAlbumsFragment.newInstance(navigator = this))
    }

    private fun initView() {
        bottomNavigationBar.setOnNavigationItemSelectedListener(this)
    }

    private fun startFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val tag = fragment::class.java.simpleName
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag(tag)?.let { foundFragment ->
            fragmentTransaction.remove(foundFragment)
        }
        fragmentTransaction
            .add(R.id.fragmentContainer, fragment, tag)
            .apply {
                if (addToBackStack)
                    addToBackStack(null)
            }
            .commit()
    }

    override fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_local_top -> {
                startFragment(TopAlbumsFragment.newInstance(navigator = this))
                true
            }
            R.id.action_search -> {
                startFragment(SearchFragment.newInstance())
                true
            }
            else -> false
        }
    }

}
