package io.github.kunal26das.kisan_network.contact.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    @ApplicationContext applicationContext: Context
): ViewModel() {

    val contactListPager = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
            enablePlaceholders = true,
        ),
        pagingSourceFactory = {
            ContactListPagingSource(applicationContext)
        }
    ).liveData.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 10
    }
}