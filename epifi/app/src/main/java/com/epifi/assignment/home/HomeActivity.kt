package com.epifi.assignment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.epifi.assignment.R
import com.epifi.assignment.model.Element
import com.epifi.assignment.preference.PreferenceModule.KEY_SHOW_BOOKMARKS
import com.epifi.assignment.ui.DetailInfoBSDFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var binding: HomeBindingImpl

    @Inject
    @Named(KEY_SHOW_BOOKMARKS)
    lateinit var showBookmarks: MutableLiveData<Boolean>

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.setLifecycleOwner(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null
        binding.setOnElementClickListener {
            showDetailInfoFragment(it)
        }
        viewModel.pager.observe(this) {
            binding.adapter.submitData(lifecycle, it)
        }
        showBookmarks.observe(this) {
            invalidateOptionsMenu()
        }
    }

    private fun showDetailInfoFragment(element: Element?) {
        DetailInfoBSDFragment()
            .setElement(element)
            .showNow(supportFragmentManager, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (showBookmarks.value) {
            true -> menuInflater.inflate(R.menu.menu_all, menu)
            else -> menuInflater.inflate(R.menu.menu_bookmarks, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all -> showBookmarks.value = false
            R.id.bookmarks -> showBookmarks.value = true
        }
        return super.onOptionsItemSelected(item)
    }

}