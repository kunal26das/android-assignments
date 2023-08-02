package com.epifi.assignment.home

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.epifi.assignment.R
import com.epifi.assignment.databinding.ActivityHomeBinding
import com.epifi.assignment.model.Element
import com.epifi.assignment.model.SearchType
import com.epifi.assignment.preference.PreferenceModule.KEY_SEARCH_QUERY
import com.epifi.assignment.preference.PreferenceModule.KEY_SEARCH_TYPE
import com.epifi.assignment.util.BindingImpl
import com.epifi.assignment.util.OnItemClickListener
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
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