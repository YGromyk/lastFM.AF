package com.gromyk.lastfmaf.presentation.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import com.gromyk.lastfmaf.R
import com.gromyk.lastfmaf.presentation.base.BaseFragment

internal class SearchFragment : BaseFragment() {
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager?

        searchView = searchItem?.actionView as SearchView?


        searchView?.setSearchableInfo(searchManager?.getSearchableInfo(activity?.componentName))

        queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                searchArtist(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                searchArtist(query)
                return true
            }

            private fun searchArtist(query: String) {

            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)

        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}