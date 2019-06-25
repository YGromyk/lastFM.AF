package com.gromyk.lastfmaf.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.navigation.Navigator
import com.gromyk.lastfmaf.presentation.navigation.Screen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        Navigator {
    private var isFirstActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.mainNavigationFragment)
        setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun onSupportNavigateUp() =
            Navigation.findNavController(this, R.id.mainNavigationFragment).navigateUp()

    private fun startFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val tag = fragment::class.java.simpleName
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag(tag)?.let { foundFragment ->
            fragmentTransaction.remove(foundFragment)
        }
        fragmentTransaction
                .add(R.id.mainNavigationFragment, fragment, tag)
                .apply {
                    if (addToBackStack)
                        addToBackStack(null)
                }
                .commit()
    }

    override fun navigateTo(screen: Int, parameters: Bundle?) {
        when (screen) {
            Screen.OPEN_WEB -> {
                val url = parameters?.getString(FragmentParameters.URL)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }
}
