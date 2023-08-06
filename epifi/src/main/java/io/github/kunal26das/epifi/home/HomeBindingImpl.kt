package io.github.kunal26das.epifi.home

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.SearchType
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_QUERY
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_TYPE
import io.github.kunal26das.epifi.util.BindingImpl
import io.github.kunal26das.epifi.util.OnItemClickListener
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import io.github.kunal26das.epifi.R
import io.github.kunal26das.epifi.databinding.ActivityHomeBinding
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class HomeBindingImpl @Inject constructor(
    @ActivityContext context: Context,
    @Named(KEY_SEARCH_QUERY) val searchQuery: MutableLiveData<String>,
    @Named(KEY_SEARCH_TYPE) val searchType: MutableLiveData<SearchType?>,
) : BindingImpl<ActivityHomeBinding>(
    LayoutInflater.from(context),
    R.layout.activity_home,
) {

    val toolbar get() = binding.toolbar
    val adapter = ElementPagingDataAdapter()
    private var onElementClickListener: OnItemClickListener<Element>? = null

    init {
        binding.impl = this
        binding.list.adapter = adapter
        binding.home.setOnClickListener {
            searchType.value = null
        }
        binding.movies.setOnClickListener {
            searchType.value = SearchType.movie
        }
        binding.series.setOnClickListener {
            searchType.value = SearchType.series
        }
        binding.episodes.setOnClickListener {
            searchType.value = SearchType.episode
        }
        adapter.setOnElementClickListener {
            onElementClickListener?.invoke(it)
        }
    }

    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        binding.lifecycleOwner = lifecycleOwner
    }

    fun setOnElementClickListener(
        onElementClickListener: OnItemClickListener<Element>
    ) {
        this.onElementClickListener = onElementClickListener
    }
}