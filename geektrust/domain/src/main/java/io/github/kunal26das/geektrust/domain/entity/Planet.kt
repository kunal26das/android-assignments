package io.github.kunal26das.geektrust.domain.entity

data class Planet constructor(
    override val name: String,
    val distance: Int,
) : NamedEntity