package com.gromyk.lastfmaf.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.albumdetails.AlbumDetailsFragment
import com.gromyk.lastfmaf.presentation.navigation.Navigator
import com.gromyk.lastfmaf.presentation.navigation.Screen
import com.gromyk.lastfmaf.presentation.search.SearchFragment
import com.gromyk.lastfmaf.presentation.topalbums.AlbumsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    Navigator,
    BottomNavigationView.OnNavigationItemSelectedListener {
    private var isFirstActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        startFragment(AlbumsFragment.newInstance(navigator = this))
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

    override fun navigateTo(screen: Int, parameters: Bundle?) {
        when (screen) {
            Screen.ARTIST_DETAILS -> {
                    startFragment(AlbumsFragment.newInstance(parameters, this))
            }
            Screen.ALBUM_DETAILS -> {
                startFragment(AlbumDetailsFragment.newInstance(parameters, this))
            }
            Screen.OPEN_WEB -> {
                val url = parameters?.getString(FragmentParameters.URL)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search)
            startFragment(SearchFragment.newInstance())
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_local_top -> {
                startFragment(AlbumsFragment.newInstance(navigator = this))
                isFirstActive = true
                true
            }
            R.id.action_search -> {
                startFragment(SearchFragment.newInstance())
                isFirstActive = false
                true
            }
            else -> false
        }
    }

}
