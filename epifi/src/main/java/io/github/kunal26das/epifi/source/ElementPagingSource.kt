package io.github.kunal26das.epifi.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.Preferences
import io.github.kunal26das.epifi.network.NetworkModule.FIRST_PAGE
import io.github.kunal26das.epifi.repository.OmdbRepository

open class ElementPagingSource(
    private val omdbRepository: OmdbRepository,
    private val preferences: Preferences,
) : PagingSource<Int, Element>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Element> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val response = omdbRepository.getList(preferences, page)
            val elements = response.search ?: emptyList()
            LoadResult.Page(
                elements,
                if (page > FIRST_PAGE) page - 1 else null,
                if (elements.isNotEmpty()) page + 1 else null,
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Element>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}