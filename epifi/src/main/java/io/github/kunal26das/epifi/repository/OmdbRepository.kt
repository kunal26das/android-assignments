package io.github.kunal26das.epifi.repository

import androidx.paging.PagingSource
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.Preferences
import io.github.kunal26das.epifi.model.Response
import io.github.kunal26das.epifi.room.OmdbDao
import io.github.kunal26das.epifi.service.OmdbService
import javax.inject.Inject

class OmdbRepository @Inject constructor(
    private val omdbService: OmdbService,
    private val omdbDao: OmdbDao,
) {

    suspend fun getList(
        preferences: Preferences,
        page: Int,
    ): Response {
        return omdbService.getList(
            searchQuery = preferences.searchQuery,
            type = preferences.searchType?.name,
            page = page,
        )
    }

    suspend fun getElement(element: Element?): Element {
        return omdbService.getElement(element?.imdbId)
    }

    fun getBookmarks(preferences: Preferences?): PagingSource<Int, Element> {
        return omdbDao.getElementsPagingSource(
            searchQuery = preferences?.searchQuery ?: "",
            searchType = preferences?.searchType?.name,
        )
    }

    suspend fun insertElement(element: Element) {
        omdbDao.insertElement(element)
    }

    suspend fun deleteElement(element: Element) {
        omdbDao.deleteElement(element)
    }

    suspend fun isElementBookmarked(element: Element): Boolean {
        return omdbDao.isElementBookmarked(element.imdbId)
    }
}