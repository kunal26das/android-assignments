package io.github.kunal26das.epifi.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Element constructor(
    /** Basic Info **/

    @PrimaryKey
    @SerializedName("imdbID")
    var imdbId: String,

    @SerializedName("Title")
    var title: String,

    @SerializedName("Year")
    var year: String,

    @SerializedName("Type")
    var _searchType: String,

    @SerializedName("Poster")
    var poster: String,

    /** Advanced Info **/

    @SerializedName("Rated")
    var rated: String,

    @SerializedName("Released")
    var released: String,

    @SerializedName("Runtime")
    var runtime: String,

    @SerializedName("Genre")
    var genre: String,

    @SerializedName("Director")
    var director: String,

    @SerializedName("Writer")
    var writer: String,

    @SerializedName("Actors")
    var actors: String,

    @SerializedName("Plot")
    var plot: String,

    @SerializedName("Language")
    var language: String,

    @SerializedName("Country")
    var country: String,

    @SerializedName("Awards")
    var awards: String,

    @Ignore
    @SerializedName("Ratings")
    var ratings: List<Rating>,

    @SerializedName("Metascore")
    var metaScore: String,

    @SerializedName("imdbRating")
    var imdbRating: String,

    @SerializedName("imdbVotes")
    var imdbVotes: String,

    @SerializedName("DVD")
    var dvd: String,

    @SerializedName("BoxOffice")
    var boxOffice: String,

    @SerializedName("Production")
    var production: String,

    @SerializedName("Website")
    var website: String,

    @Ignore
    @SerializedName("Response")
    private var _response: String,

    /** Local Info **/

    @SerializedName("bookmarked")
    var isBookmarked: Boolean,
) : Parcelable {

    constructor() : this(
        imdbId = "",
        title = "",
        year = "",
        _searchType = "",
        poster = "",
        rated = "",
        released = "",
        runtime = "",
        genre = "",
        director = "",
        writer = "",
        actors = "",
        plot = "",
        language = "",
        country = "",
        awards = "",
        ratings = emptyList(),
        metaScore = "",
        imdbRating = "",
        imdbVotes = "",
        dvd = "",
        boxOffice = "",
        production = "",
        website = "",
        _response = "",
        isBookmarked = false
    )

    val cast
        get() = try {
            actors.split(",").joinToString("\n") { it.trim() }
        } catch (e: Throwable) {
            actors
        }

    val searchType
        get() = try {
            SearchType.valueOf(_searchType)
        } catch (e: Throwable) {
            null
        }

    val randomRating
        get() = try {
            ratings.random()
        } catch (e: Throwable) {
            null
        }

    val response
        get() = _response.toBoolean()

    class DIffCallback : DiffUtil.ItemCallback<Element>() {
        override fun areItemsTheSame(oldItem: Element, newItem: Element): Boolean {
            return oldItem.imdbId == newItem.imdbId
        }

        override fun areContentsTheSame(oldItem: Element, newItem: Element): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val KEY_ELEMENT = "element"
    }
}
