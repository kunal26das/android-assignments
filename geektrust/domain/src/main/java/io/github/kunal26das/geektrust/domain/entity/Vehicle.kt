package io.github.kunal26das.geektrust.domain.entity

data class Vehicle constructor(
    internal val id: Int,
    override val name: String,
    val maxDistance: Int,
    val speed: Int,
) : NamedEntity
