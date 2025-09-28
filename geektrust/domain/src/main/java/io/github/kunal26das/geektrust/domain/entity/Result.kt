package io.github.kunal26das.geektrust.domain.entity

sealed class Result {
    data class Success(
        val planet: Planet,
        val timeTaken: Int,
    ) : Result()

    data object Failure : Result()
    data class Error(val message: String) : Result()
    data object Unknown : Result()
}
