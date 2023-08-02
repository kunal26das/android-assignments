package com.naviapp.assignment.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pull(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("closed_at")
    val closedAt: String? = null,

    @SerializedName("user")
    val user: User? = null,
) : Parcelable {

    class DiffCallback : DiffUtil.ItemCallback<Pull>() {
        override fun areItemsTheSame(oldItem: Pull, newItem: Pull): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pull, newItem: Pull): Boolean {
            return oldItem == newItem
        }
    }

}
