package io.github.kunal26das.kisan_network.message

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    val displayName: String,
    val otp: String,
    @PrimaryKey
    val timestamp: Long = System.currentTimeMillis(),
)
