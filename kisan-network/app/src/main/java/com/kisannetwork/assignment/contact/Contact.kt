package com.kisannetwork.assignment.contact

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val displayName: String,
    val phoneNumber: String,
    val countryCode: String,
) : Parcelable {

    val number
        get() = "${countryCode}${phoneNumber}"

    companion object {
        const val KEY_CONTACT = "contact"
    }
}
