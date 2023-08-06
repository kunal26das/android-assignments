package io.github.kunal26das.epifi.model

@Suppress("EnumEntryName")
enum class SearchType {
    movie, series, episode;

    companion object {

        operator fun get(searchType: String?) = try {
            valueOf(searchType!!)
        } catch (e: Throwable) {
            null
        }

    }
}