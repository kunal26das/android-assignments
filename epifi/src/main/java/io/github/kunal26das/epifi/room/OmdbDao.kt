package io.github.kunal26das.epifi.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.kunal26das.epifi.model.Element

@Dao
interface OmdbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElement(element: Element)

    @Delete
    suspend fun deleteElement(element: Element)

    @Query("SELECT EXISTS (SELECT 1 FROM Element WHERE imdbId = :imdbId)")
    suspend fun isElementBookmarked(imdbId: String): Boolean

    @Query(
        "SELECT * FROM Element " +
                "WHERE (title LIKE '%' || :searchQuery || '%')" +
                "AND ((:searchType IS NULL) or (_searchType = :searchType))"
    )
    fun getElementsPagingSource(
        searchQuery: String,
        searchType: String? = null,
    ): PagingSource<Int, Element>
}