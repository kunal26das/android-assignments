package io.github.kunal26das.epifi.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SHOW_BOOKMARKS
import io.github.kunal26das.epifi.ui.DetailInfoBSDFragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.epifi.R
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

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, HomeActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}