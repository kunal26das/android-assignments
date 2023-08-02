package com.epifi.assignment.model

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